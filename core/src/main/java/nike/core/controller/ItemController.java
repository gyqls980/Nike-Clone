package nike.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nike.core.domain.Item;
import nike.core.service.ItemService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> find(@RequestParam("target") String target, @RequestParam("upper") String upper,
                                    @RequestParam(value="lower", required = false, defaultValue = "") String lower) {
        List<Item> items;
        if (lower.equals("")){
            items = itemService.findItems(target, upper);
        } else {
            items = itemService.findItems(target, upper, lower);
        }
        return items;
    }

    @GetMapping("/{itemId}")
    public Item findById(@PathVariable("itemId") Long itemId){
        return itemService.findOne(itemId);
    }

}
