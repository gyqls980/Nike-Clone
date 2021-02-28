package nike.core.Controller;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Item;
import nike.core.service.ItemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @RequestMapping("/{target}/{lower}")
    @ResponseBody
    public List<Item> getItems(@PathVariable("target") String target,
                               @PathVariable("lower") String lower) {
        List<Item> items = itemService.findItems(target, lower);
        return items;
    }

}
