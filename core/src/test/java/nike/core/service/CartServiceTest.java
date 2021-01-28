package nike.core.service;

import nike.core.domain.Cart;
import nike.core.domain.Member;
import nike.core.repository.ItemRepository;
import nike.core.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartServiceTest {

    @Autowired CartService cartService;
    @Autowired ItemRepository itemRepository;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 장바구니조회() {
        System.out.println("장바구니조회 = " + cartService.findOne(21L));
    }

    @Test(expected = IllegalStateException.class)
    public void 장바구니중복등록() {
        Cart cart1 = new Cart();
        cart1.setItem(itemRepository.findItemById(1L));
        cart1.setMember(memberRepository.findMember(1L));
        cart1.setItemCount(1);
        cart1.setItemPrice(itemRepository.findItemById(1L).getPrice());

        Long cart1Id = cartService.saveCart(cart1);

    }

    @Test
    public void 장바구니수정(){
        Cart cart1 = cartService.findOne(21L);
        cart1.setItemCount(2);
        Cart cart2 = cartService.findOne(21L);
        Assertions.assertEquals(2, cart2.getItemCount());
    }



}