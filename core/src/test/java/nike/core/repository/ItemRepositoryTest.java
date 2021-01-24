package nike.core.repository;

import nike.core.domain.Category;
import nike.core.domain.Item;
import nike.core.service.ItemService;
import org.junit.experimental.categories.Categories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepositoryTest {
    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;
    @Test
    public void findItem() {
//        //Given
//        Category cate1 = new Category();
//        Item item1 = new Item();
//        //When
//        itemRepository.save(item1);
//        //Then
//        Assertions.assertEquals(item1, itemRepository.findItemById(1L));
    }
}