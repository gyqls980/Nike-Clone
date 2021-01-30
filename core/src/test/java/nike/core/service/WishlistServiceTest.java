package nike.core.service;

import nike.core.domain.Item;
import nike.core.domain.Member;
import nike.core.domain.OrderStatus;
import nike.core.repository.ItemRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WishlistServiceTest {

    @Autowired EntityManager em;
    @Autowired WishlistService wishlistService;
    @Autowired ItemRepository itemRepository;

    @Test
    @DisplayName("위시리스트 등록 테스트")
    public void 위시리스트_등록(){
        //given
        Member member = new Member();
        member.setEmail("a@naver.com");
        member.setPassword("5678");
        member.setName("지연");
        member.setPhone("010-2222-2222");
        em.persist(member);

        Item item = itemRepository.findItemById(1L);

        //when
        wishlistService.saveWishlist(member, item);
    }

}
