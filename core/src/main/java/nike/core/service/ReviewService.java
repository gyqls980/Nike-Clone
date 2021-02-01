package nike.core.service;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Review;
import nike.core.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 리뷰등록
    @Transactional
    public Long create(Review review) {
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
    public void remove(Review review){
        reviewRepository.remove(review);
    }

    // 리뷰조회
    public Review findOne(Long reviewId){
        return reviewRepository.findReviewById(reviewId);
    }

    // 멤버별 리뷰조회
}
