package nike.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // new 써서 설정 방지
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //주문 회원

    private String email;

    private String address;

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [READY, DELIVERING, COMP, CANCEL]

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // 생성 메서드 //
    public static Order createOrder(Member member, String address, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setAddress(address);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        // TODO(HYOBIN) : OrderStatus 상태 어떻게 할건지
        order.setStatus(OrderStatus.READY);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

}
