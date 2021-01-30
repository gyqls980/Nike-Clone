package nike.core.domain;

import lombok.Getter;
import lombok.Setter;
import nike.core.repository.WishlistRepository;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Wishlist {
    @Id
    @GeneratedValue
    @Column(name = "wishlist_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void setMember(Member member) {
        this.member = member;
        member.getWishlists().add(this);
    }

    public static Wishlist createWishlist(Member member, Item item){
        Wishlist wishlist = new Wishlist();
        wishlist.setMember(member);
        wishlist.setItem(item);
        return wishlist;
    }

}
