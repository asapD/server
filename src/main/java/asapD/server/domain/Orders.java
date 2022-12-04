package asapD.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Arrays;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Orders extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public void addOrderItem(OrderItem orderItem) {

        this.orderItems.add(orderItem);
        orderItem.setOrders(this);

    }

    public static Orders createOrder(Member member, Delivery delivery,
        List<OrderItem> orderItem) {

        Orders orders = new Orders();
        orders.setMember(member);
        orders.setDelivery(delivery);
        orderItem.forEach(orders::addOrderItem);

        return orders;
    }
}
