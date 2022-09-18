package asapD.server.controller.dto.store;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreResponseDto {
    private String name;
    private int owner;
    private String address;
}
