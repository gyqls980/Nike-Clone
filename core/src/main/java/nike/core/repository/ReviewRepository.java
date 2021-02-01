package nike.core.repository;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Order;
import nike.core.domain.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager em;

    public void save(Review review) {
        em.persist(review);
    }
    public Review findReviewById(Long id) {
        return em.find(Review.class, id);
    }
    public void remove(Review review) {
        em.remove(review);
    }
}
