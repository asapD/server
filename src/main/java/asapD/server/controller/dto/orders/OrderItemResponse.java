package asapD.server.controller.dto.orders;

import asapD.server.domain.OrderItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {

  private long itemId;
  private int orderPrice;
  private int count;

  public OrderItemResponse(OrderItem orderItem) {
    itemId = orderItem.getId();
    orderPrice = orderItem.getOrderPrice();
    count = orderItem.getCount();
  }
}
