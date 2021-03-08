package nike.core.repository;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Category;
import nike.core.domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Item findItemById(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findItemsByCategory(Category category) {
        return em.createQuery("select i from Item i where i.category in :category", Item.class)
                .setParameter("category", category)
                .getResultList();
    }

    public List<Item> findItemsByCategories(List<Category> categories) {
        return em.createQuery("select i from Item i where i.category in :categories", Item.class)
                .setParameter("categories", categories)
                .getResultList();
    }

}

