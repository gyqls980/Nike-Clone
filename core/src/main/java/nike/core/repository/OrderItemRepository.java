package nike.core.repository;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Member;
import nike.core.domain.Order;
import nike.core.domain.OrderItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemRepository {
    private final EntityManager em;

    public List<OrderItem> findOrderItems(Order order){
        return em.createQuery("select oi from OrderItem oi where oi.order = :order", OrderItem.class)
                .setParameter("order", order)
                .getResultList();
    }
}
