package nike.core.repository;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Category;
import nike.core.domain.Item;
import nike.core.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }
    public Item findItemById(Long id) {
        return em.find(Item.class, id);
    }
    public List<Item> findItemsByCategory(Category category) {
        return em.createQuery("select i from Item i where i.category = :category", Item.class).getResultList();
    }

}

