package nike.core.service;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Item;
import nike.core.domain.Member;
import nike.core.domain.Order;
import nike.core.domain.OrderItem;
import nike.core.repository.ItemRepository;
import nike.core.repository.MemberRepository;
import nike.core.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, String address, List<Long> itemId, int count) {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        //엔티티 조회
        Member member = memberRepository.findMember(memberId);
        for (Long itemid : itemId) {
            Item item = itemRepository.findItemById(itemid);
            //주문상품 생성
            OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
            orderItems.add(orderItem);
        }
        //주문 생성
        Order order = Order.createOrder(member, address, orderItems);
        //주문 저장
        // order 설정을 cascade해서 이거만 저장해도 orderItem, Delivery 자동 저장된다.
        orderRepository.save(order);
        return order.getId();
    }

    // 주문 검색
    public List<Order> findOrders(Member member) {
        return orderRepository.findAllOrder(member);
    }

    // 주문 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }
}
