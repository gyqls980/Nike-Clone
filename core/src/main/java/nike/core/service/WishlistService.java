package nike.core.service;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Item;
import nike.core.domain.Member;
import nike.core.domain.Wishlist;
import nike.core.repository.WishlistRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public Long saveWishlist(Member member, Item item){
        Wishlist wishlist = Wishlist.createWishlist(member, item);
        wishlistRepository.save(wishlist);

        return wishlist.getId();
    }

    public void removeWishlist(Long id){
        Wishlist wishlist = wishlistRepository.findWishlist(id);
        wishlistRepository.remove(wishlist);
    }
}
