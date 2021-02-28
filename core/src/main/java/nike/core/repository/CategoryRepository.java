package nike.core.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import nike.core.domain.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public Category findCategoryById(Long id) {
        return em.find(Category.class, id);
    }

    public List<Category> findCategoryByTarget(String target) {
        return em.createQuery("select c from Category c where c.target = :target", Category.class)
                .setParameter("target", target)
                .getResultList();
    }

    public List<Category> findCategoryByUpper(String upper) {
        return em.createQuery("select c from Category c where c.upperCase = :upper", Category.class)
                .setParameter("upper", upper)
                .getResultList();
    }

    public List<Category> findCategoryByLower(String lower) {
        return em.createQuery("select c from Category c where c.lowerCase = :lower", Category.class)
                .setParameter("lower", lower)
                .getResultList();
    }

    public Category findCategoryByTargetNLower(String target, String lower) {
        return em.createQuery("select c from Category c where c.target = :target and (c.lowerCase = :lower or c.upperCase=:lower)", Category.class)
                .setParameter("target", target)
                .setParameter("lower", lower)
                .getResultList().get(0);
    }
}
