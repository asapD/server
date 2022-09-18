package asapD.server.controller.dto.item;

import asapD.server.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class ItemResponseDto {
    private String name;
    private String description;
    private int price;
    private Long storeId;
}
