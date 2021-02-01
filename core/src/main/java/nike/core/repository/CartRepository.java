package nike.core.repository;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Cart;
import nike.core.domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    private final EntityManager em;

    public void save(Cart cart) {
        em.persist(cart);
    }
    public Cart findCartById(Long id) {
        return em.find(Cart.class, id);
    }

    public List<Cart> findCartsByItem(Item item) {
        return em.createQuery("select c from Cart c where c.item = :item", Cart.class)
                .setParameter("item", item)
                .getResultList();
    }


    public void remove(Long id){
        em.remove(findCartById(id));
    }

}
