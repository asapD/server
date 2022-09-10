package asapD.server.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Item extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private int price;
}
