package nike.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static Wishlist createWishlist(Member member, Item item){
        Wishlist wishlist = new Wishlist();
        wishlist.setMember(member);
        wishlist.setItem(item);
        return wishlist;
    }

}
