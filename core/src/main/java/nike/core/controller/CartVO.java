package nike.core.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartVO {
    private Long memberId;
    private Long itemId; // 주문 item
    private int itemCount; //주문 수량
}
