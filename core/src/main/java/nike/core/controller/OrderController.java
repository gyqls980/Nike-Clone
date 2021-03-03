package nike.core.controller;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Member;
import nike.core.domain.Order;
import nike.core.domain.OrderItem;
import nike.core.repository.MemberRepository;
import nike.core.repository.OrderItemRepository;
import nike.core.repository.OrderRepository;
import nike.core.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/order")
    public Long order(@RequestBody OrderForm form){
        Long memberId = form.getMemberId();
        String address = form.getAddress();
        List<Long> itemId = form.getItemId();
        List<Integer> count = form.getCount();
        Long orderId = orderService.order(memberId, address, itemId, count);

        return orderId;
    }

    @GetMapping("/orders")
    public List<Order> findOrders(@RequestParam("memberId") Long memberId){
        Member member = memberRepository.findMember(memberId);
        List<Order> orders = orderService.findOrders(member);

        return orders;
    }

    @GetMapping("/order")
    public String cancelOrder(@RequestParam("orderId") Long orderId){
        orderService.cancelOrder(orderId);

        return "Success";
    }

    @GetMapping("/orderItems")
    public List<OrderItem> findOrderItems(@RequestParam("orderId") Long orderId){
        Order order = orderRepository.findOne(orderId);
        List<OrderItem> orderItems = orderItemRepository.findOrderItems(order);

        return orderItems;
    }
}
