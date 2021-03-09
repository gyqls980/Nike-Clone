package nike.core.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewVO {

    private Long memberId;
    private Long itemId;
    private Integer star;
    private String comment;
}
