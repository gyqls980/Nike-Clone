package nike.core.service;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Category;
import nike.core.domain.Item;
import nike.core.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    public Item findOne(Long id) {
        return itemRepository.findItemById(id);
    }

    public List<Item> findItems(Category category) {
        return itemRepository.findItemsByCategory(category);
    }

}
