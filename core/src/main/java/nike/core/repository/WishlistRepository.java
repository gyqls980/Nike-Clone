package nike.core.repository;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Wishlist;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class WishlistRepository {

    private final EntityManager em;

    public void save(Wishlist wishlist){
        em.persist(wishlist);
    }
}
