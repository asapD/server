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

    private long downId;

    private String destination;


    public static Delivery createDelivery(String destination) {

        Delivery delivery = new Delivery();
        delivery.setDownId(1);
        delivery.destination = destination;

        return delivery;
    }
}
