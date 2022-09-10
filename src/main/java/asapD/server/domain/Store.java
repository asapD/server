package asapD.server.domain;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Store extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    private String name;

    private int owner;

    private String address;

    @OneToMany(mappedBy = "store")
    List<Item> items = new ArrayList<>();


}
