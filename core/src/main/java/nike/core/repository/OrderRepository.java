package nike.core.repository;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Member;
import nike.core.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAllOrder(Member member){
        return em.createQuery("select o from Order o where o.member = :member", Order.class)
                .setParameter("member", member)
                .getResultList();
    }

}
