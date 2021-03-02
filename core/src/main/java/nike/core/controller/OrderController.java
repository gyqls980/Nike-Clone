package nike.core.controller;

import lombok.RequiredArgsConstructor;
import nike.core.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public Long order(@RequestBody OrderForm form){
        Long memberId = form.getMemberId();
        String address = form.getAddress();
        List<Long> itemId = form.getItemId();
        List<Integer> count = form.getCount();
        Long orderId = orderService.order(memberId, address, itemId, count);

        return orderId;
    }

}
