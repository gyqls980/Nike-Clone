package nike.core.service;

import lombok.RequiredArgsConstructor;
import nike.core.domain.Item;
import nike.core.domain.Member;
import nike.core.domain.Wishlist;
import nike.core.repository.WishlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    @Transactional
    public Long saveWishlist(Member member, Item item){
        Wishlist wishlist = Wishlist.createWishlist(member, item);
        wishlistRepository.save(wishlist);

        return wishlist.getId();
    }

    @Transactional
    public void removeWishlist(Long id){
        Wishlist wishlist = wishlistRepository.findWishlist(id);
        wishlistRepository.remove(wishlist);
    }
}
