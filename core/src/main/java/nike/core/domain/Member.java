package nike.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String name;
    private String phone;

    /*
    [Could not write JSON 오류]
    해결방법 1.
    - 해당 Object를 JSON 타입으로 변환을 하기 위해서는 FetchType.LAZY 설정을 지운다.
    해결방법 2.
    - 해당 Object를 JSON으로 변환하지 않는다면, @JsonIgnore 어노테이션을 nested 객체에 붙인다.
    */

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Wishlist> wishlists = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Cart> carts = new ArrayList<>();

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        // TODO(HYOBIN) : security 활용으로 수정
        // this.password = new BCryptPasswordEncoder().encode(password);
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    /*
    // 생성자 메서드
    public static Member createMember(String email, String password, String name, String phone){
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setName(name);
        member.setPhone(phone);

        return member;
    }*/
}