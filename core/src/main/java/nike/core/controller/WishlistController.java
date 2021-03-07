package nike.core.controller;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Item;
import nike.core.domain.Member;
import nike.core.repository.ItemRepository;
import nike.core.repository.MemberRepository;
import nike.core.service.WishlistService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // member 객체를 받아야 하는지 아니면 id만 받고 여기서 객체를 조회해야 하는지
    @PutMapping("/save-wishlist/{memberId}/{itemId}")
    @ResponseBody
    public Long saveWishlist(@PathVariable(name="memberId") Long memberId, @PathVariable(name="itemId") Long itemId){
        Member member = memberRepository.findMember(memberId);
        Item item = itemRepository.findItemById(itemId);

        Long wishlistId = wishlistService.saveWishlist(member, item);

        return wishlistId;
    }

}
