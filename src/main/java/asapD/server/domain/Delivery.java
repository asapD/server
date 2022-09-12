package asapD.server.domain;

import lombok.Getter;

import javax.persistence.*;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
//    private Order order;

    private long downId;


}
