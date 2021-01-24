package nike.core.domain;

import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private int price;

    private String image;

    private String size;

    private String detail;

    private Boolean onlyMember;

}
