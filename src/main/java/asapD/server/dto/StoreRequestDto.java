package asapD.server.dto;

import asapD.server.domain.Store;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class StoreRequestDto {
    private String name;

    private int owner;

    private String address;

    public Store toEntity(){
        return Store.builder()
                .name(name)
                .owner(owner)
                .address(address)
                .build();
    }
}
