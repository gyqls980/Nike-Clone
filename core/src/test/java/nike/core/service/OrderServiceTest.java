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
//        em.persist(member2);

        Item item = itemRepository.findItemById(1L);

        int orderCount=2;
        String addr = "서울시 국민대학교";

        //when
        Long orderId = orderService.order(member.getId(), addr, item.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.READY, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",1, getOrder.getOrderItems().size());
    }
}
