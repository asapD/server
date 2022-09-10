package asapD.server.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

//    @ManyToOne
//    @JoinColumn(name = "store_id")
//    private Store store;

    private String destination;

}
