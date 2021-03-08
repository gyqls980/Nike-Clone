package nike.core.service;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Category;
import nike.core.domain.Item;
import nike.core.repository.CategoryRepository;
import nike.core.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public Item findOne(Long id) {
        return itemRepository.findItemById(id);
    }

    public List<Item> findItems(String target, String upper) {
        List<Category> categories = categoryRepository.findCategoryByTwo(target, upper);
        return itemRepository.findItemsByCategories(categories);
    }

    public List<Item> findItems(String target, String upper, String lower) {
        Optional<Category> category = categoryRepository.findCategoryByAll(target, upper, lower);
        if (category.isEmpty()) {
            return Collections.<Item>emptyList();
        }
        return itemRepository.findItemsByCategory(category.get());
    }

}
