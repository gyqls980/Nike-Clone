package nike.core.repository;

import nike.core.domain.Cart;
import nike.core.domain.Item;
import nike.core.service.ItemService;
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
public class CartRepositoryTest {

    @Autowired CartRepository cartRepository;
    @Autowired ItemService itemService;
    @Autowired MemberRepository memberRepository;
    @Autowired CategoryRepository categoryRepository;

    @Test
    public void 장바구니추가및조회() {
        Cart cart1 = new Cart();
        cart1.setItem(itemService.findOne(1L));
        cart1.setMember(memberRepository.findMember(1L));
        cart1.setItemCount(1);

        cartRepository.save(cart1);

        Assertions.assertEquals(cart1, cartRepository.findCartById(cart1.getId()) );
    }

    @Test
    public void 장바구니id로조회(){
        System.out.println("cartRepository = " + cartRepository.findCartById(21L));
    }

    @Test
    public void 장바구니여러개조회(){
        System.out.println("carts : " + cartRepository.findCartsByItem(itemService.findOne(1L)));
    }

    @Test
    public void 장바구니여러개조회_비었을때(){
        System.out.println("carts : " + cartRepository.findCartsByItem(itemService.findOne(2L)));
    }

}