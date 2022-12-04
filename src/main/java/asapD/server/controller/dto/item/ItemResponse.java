package asapD.server.controller.dto.item;

import asapD.server.domain.Item;
import lombok.Data;

@Data
public class ItemResponse {

    private Long itemId;
    private String name;
    private String description;
    private int price;
    private Long storeId;

    public ItemResponse(Item item) {
        itemId = item.getId();
        name = item.getName();
        description = item.getDescription();
        price = item.getPrice();
        storeId = item.getStore().getId();
    }
}
