package nike.core.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import nike.core.domain.Cart;
import nike.core.domain.Item;
import nike.core.domain.Member;
import nike.core.repository.CartRepository;
import nike.core.repository.ItemRepository;
import nike.core.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    // 장바구니등록
    @Transactional
    public Long saveCart(Member member, Item item, int itemCount){
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setItem(item);
        cart.setItemCount(itemCount);
        validateDuplicateCart(cart);
        cartRepository.save(cart);
        return cart.getId();
    }

    // 장바구니등록 시 중복방지 (Item 체크)
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

    // 멤버별 상품조회
    public List<Cart> findbyMember(Member member) {
        return member.getCarts();
    }

    // 상품삭제
    public void removeCart(Long cartId){
        cartRepository.remove(cartId);
    }

    // 상품수량변경
    public void updateCount(Long cartId, Integer count){
        Cart cartById = cartRepository.findCartById(cartId);
        cartById.setItemCount(count);
    }
}
