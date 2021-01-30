package nike.core.repository;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Member;
import nike.core.domain.Wishlist;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WishlistRepository {

    private final EntityManager em;

    public void save(Wishlist wishlist){
        em.persist(wishlist);
    }

    public List<Wishlist> findWishlists(Member member){
        return em.createQuery("select w from Wishlist w where w.member =:member", Wishlist.class)
                .setParameter("member", member)
                .getResultList();
    }

    public Wishlist findWishlist(Long id){
        return em.find(Wishlist.class, id);
    }
}
