package nike.core.service;

import nike.core.domain.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired ItemService itemService;
    @Test
    public void findItems() {
        System.out.println("item : " + itemService.findOne(1L).getName());
    }


    @Test
    public void findItemsByAll() {
        for(Item i : itemService.findItems("woman", "shoes", "running")){
            System.out.println("i. = " + i.getName());
        }
    }

    @Test
    public void findItemsByTwo() {
        for(Item i : itemService.findItems("woman", "shoes")){
            System.out.println("i. = " + i.getName());
        }
    }
}