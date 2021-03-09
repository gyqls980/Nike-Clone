package nike.core.controller;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Cart;
import nike.core.domain.Item;
import nike.core.domain.Member;
import nike.core.repository.MemberRepository;
import nike.core.service.CartService;
import nike.core.service.ItemService;
import nike.core.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final MemberRepository memberRepository;
    private final ItemService itemService;
    private final CartService cartService;

    @PostMapping
    public Long create(@RequestBody CartVO cartVO) {
        Member member = memberRepository.findMember(cartVO.getMemberId());
        Item item = itemService.findOne(cartVO.getItemId());
        Long cartId = cartService.saveCart(member, item, cartVO.getItemCount());
        return cartId;
    }

    @GetMapping
    public List<Cart> find(@RequestParam(name="memberId") Long memberId){
        Member member = memberRepository.findMember(memberId);
        List<Cart> carts = cartService.findbyMember(member);
        return carts;
    }

    @PutMapping("/{cartId}")
    public Long update(@PathVariable(name="cartId") Long cartId, @RequestBody CartVO cartVO) {
        cartService.updateCount(cartId, cartVO.getItemCount());
        return cartId;
    }

    @DeleteMapping("/{cartId}")
    public String delete(@PathVariable(name="cartId") Long cartId){
        cartService.removeCart(cartId);
        return "success";
    }

}
