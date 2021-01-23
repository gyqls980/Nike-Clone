package nike.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String target;

    private String upperCase;

    private String lowerCase;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();
}
