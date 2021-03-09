package nike.core.service;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Item;
import nike.core.domain.Member;
import nike.core.domain.Review;
import nike.core.repository.MemberRepository;
import nike.core.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    // 리뷰등록
    @Transactional
    public Long create(Member member, Item item, Integer star, String coment) {
        Review review = new Review();
        review.setMember(member);
        review.setItem(item);
        review.setStar(star);
        review.setComment(coment);
        review.setReviewDate(LocalDateTime.now());
        reviewRepository.save(review);
        return review.getId();
    }

    // 리뷰수정
    @Transactional
    public Long update(Long id, String comment, Integer star) {
        Review reviewById = reviewRepository.findReviewById(id);
        reviewById.setReviewDate(LocalDateTime.now());
        reviewById.setComment(comment);
        reviewById.setStar(star);
        return reviewById.getId();
    }

    // 리뷰삭제
    @Transactional
    public void remove(Long reviewId){
        Review review = reviewRepository.findReviewById(reviewId);
        reviewRepository.remove(review);
    }

    // 리뷰조회
    public Review findOne(Long reviewId){
        return reviewRepository.findReviewById(reviewId);
    }

    // 멤버별 리뷰조회
    public List<Review> findReviews(Long memberId){
        Member member = memberRepository.findMember(memberId);
        return member.getReviews();
    }

}
