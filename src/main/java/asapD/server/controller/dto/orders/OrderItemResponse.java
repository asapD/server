package asapD.server.controller.dto.orders;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {

  private long itemId;
  private long orderId;
  private int orderPrice;
  private int count;
}
