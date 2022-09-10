package asapD.server.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Store extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "owner")
    private int owner;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "store")
    List<Item> items = new ArrayList<>();

    @Builder
    public Store(String name, int owner, String address){
        this.name = name;
        this.owner = owner;
        this.address = address;
    }

}
