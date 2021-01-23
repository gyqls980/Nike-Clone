package nike.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; //주문

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id") //연관관계 매핑(얘가주인)
    private Item item; //주문 상품

    private int itemPrice; //주문 가격
    private int itemCount; //주
}
