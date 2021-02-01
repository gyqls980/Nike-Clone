package nike.core.service;

import nike.core.domain.Review;
import nike.core.repository.ItemRepository;
import nike.core.repository.MemberRepository;
import nike.core.repository.ReviewRepository;
import org.junit.Test;
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
public class ReviewServiceTest {

    @Autowired private ReviewService reviewService;
    @Autowired private MemberRepository memberRepository;
    @Autowired private ItemRepository itemRepository;

    // 리뷰등록
    @Test
    public void 리뷰등록() {
        Review review1 = new Review();
        review1.setStar(5);
        review1.setMember(memberRepository.findMember(16L));
        review1.setItem(itemRepository.findItemById(1L));
        review1.setComment("최고");

        Long review1Id = reviewService.create(review1);
        assertEquals(review1, reviewService.findOne(review1Id));;

    }

    @Test
    public void 리뷰수정() {
        Review review1 = reviewService.findOne(23L);
        reviewService.update(review1.getId(), "최고에요", 4);
        assertEquals(4, review1.getStar(), 0);
    }

    @Test
    public void 리뷰삭제() {
        Review review1 = reviewService.findOne(23L);
        reviewService.remove(review1);
        reviewService.findOne(23L);
    }
}