package asapD.server.controller.dto.store;

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

}
