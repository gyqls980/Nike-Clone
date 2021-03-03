package nike.core.controller;

import lombok.Getter;
import lombok.Setter;
import nike.core.domain.Member;

import java.util.List;

@Getter @Setter
public class OrderForm {

    private Long memberId;
    private String address;
    private List<Long> itemId;
    private List<Integer> count;
}