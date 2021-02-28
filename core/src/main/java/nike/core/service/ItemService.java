package nike.core.service;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Category;
import nike.core.domain.Item;
import nike.core.repository.CategoryRepository;
import nike.core.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public Item findOne(Long id) {
        return itemRepository.findItemById(id);
    }

    public List<Item> findItems(String target, String lower) {
        Category category = categoryRepository.findCategoryByTargetNLower(target, lower);
        return itemRepository.findItemsByCategory(category);
    }

}
