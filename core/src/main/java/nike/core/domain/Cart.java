package nike.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Cart {
    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //주문

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id") //연관관계 매핑(얘가주인)
    private Item item; //주문 상품

    private int itemPrice; //주문 가격
    private int itemCount; //주문 수량

}
