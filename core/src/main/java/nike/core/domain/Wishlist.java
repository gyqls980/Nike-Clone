package nike.core.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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

}
