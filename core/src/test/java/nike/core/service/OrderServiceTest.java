package nike.core.service;

import nike.core.domain.Item;
import nike.core.domain.Member;
import nike.core.domain.Order;
import nike.core.domain.OrderStatus;
import nike.core.repository.ItemRepository;
import nike.core.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Autowired ItemRepository itemRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = new Member();
        member.setEmail("a@naver.com");
        member.setPassword("5678");
        member.setName("지연");
        member.setPhone("010-2222-2222");
        em.persist(member);

        Item item1 = itemRepository.findItemById(1L);
        Item item2 = itemRepository.findItemById(2L);

        int orderCount=2;
        String addr = "서울시 국민대학교";

        //when
        Long orderId = orderService.order(member.getId(), addr, List.of(item1.getId(), item2.getId()), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        System.out.print("주문" + getOrder.getMember() + " 주소 : " + getOrder.getAddress());

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.READY, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",2, getOrder.getOrderItems().size());
    }

    @Test
    public void 주문조회() {
        Member member = new Member();
        member.setEmail("a@naver.com");
        member.setPassword("5678");
        member.setName("지연");
        member.setPhone("010-2222-2222");
        em.persist(member);

        Item item = itemRepository.findItemById(1L);

        int orderCount=2;
        String addr = "서울시 국민대학교";

        //when
        Long orderId = orderService.order(member.getId(), addr, List.of(item.getId()), orderCount);
        Order getOrder = orderRepository.findOne(orderId);

        List<Order> orderlist = orderService.findOrders(member);

        System.out.println("주문 주소 : " + getOrder.getAddress() + " / 조회 주문 주소 : " + orderlist.get(0).getAddress());
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.READY, orderlist.get(0).getStatus());
    }

    @Test
    public void 주문취소(){
        Member member = new Member();
        member.setEmail("a@naver.com");
        member.setPassword("5678");
        member.setName("지연");
        member.setPhone("010-2222-2222");
        em.persist(member);

        Item item = itemRepository.findItemById(1L);

        int orderCount=2;
        String addr = "서울시 국민대학교";
        Long orderId = orderService.order(member.getId(), addr, List.of(item.getId()), orderCount);

        orderService.cancelOrder(orderId);
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("주문 취소 시 상태는 CANCEL이다.", OrderStatus.CANCEL, getOrder.getStatus());
    }
}
