package nike.core.Controller;

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
    public List<Item> getItemsByTwo(@RequestParam("target") String target, @RequestParam("upper") String upper,
                                    @RequestParam(value="lower", required = false, defaultValue = "") String lower) {
        List<Item> items;
        log.info(lower);
        if (lower.equals("")){
            log.info("no");
            items = itemService.findItems(target, upper);
        } else {
            log.info("else");
            items = itemService.findItems(target, upper, lower);
        }
        return items;
    }

//    @GetMapping("item")
//    public List<Item> getItemsByAll(@RequestParam("target") String target, @RequestParam("upper") String upper,
//                                    @RequestParam("lower") String lower) {
//        log.info("okay");
//        List<Item> items = itemService.findItems(target, upper, lower);
//        return items;
//    }

}
