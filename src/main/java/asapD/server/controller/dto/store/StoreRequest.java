package asapD.server.controller.dto.store;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class StoreRequest {

    private String name;
    private int owner;
    private String address;

}
