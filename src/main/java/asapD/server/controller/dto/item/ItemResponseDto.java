package asapD.server.controller.dto.item;

import asapD.server.domain.Item;
import asapD.server.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ItemResponseDto {

    private Long itemId;
    private String name;
    private String description;
    private int price;
    private Long storeId;

    public ItemResponseDto(Item item) {
        itemId = item.getId();
        name = item.getName();
        description = item.getDescription();
        price = item.getPrice();
        storeId = item.getStore().getId();
    }
}
