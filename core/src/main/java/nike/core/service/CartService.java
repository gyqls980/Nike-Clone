package nike.core.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import nike.core.domain.Cart;
import nike.core.domain.Item;
import nike.core.repository.CartRepository;
import nike.core.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    // 상품등록
    @Transactional
    public Long saveCart(Cart cart){
        validateDuplicateCart(cart);
        cartRepository.save(cart);
        return cart.getId();
    }


    // 상품등록 시 중복방지 (Item 체크)
    private void validateDuplicateCart(Cart cart) {
        Item tempItem = itemRepository.findItemById(cart.getItem().getId());
        List<Cart> cartsByItem = cartRepository.findCartsByItem(tempItem);
        if (!cartsByItem.isEmpty()) {
            throw new IllegalStateException("장바구니에 이미 존재합니다.");
        }
    }

    // 상품조회
    public Cart findOne(Long id) {
        return cartRepository.findCartById(id);
    }

    // 상품삭제
    public void removeCart(Cart cart){
        cartRepository.remove(cart.getId());
    }


}
