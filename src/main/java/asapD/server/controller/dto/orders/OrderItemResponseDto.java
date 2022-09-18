package asapD.server.controller.dto.orders;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponseDto {

  private Long itemId;
  private Long orderId;
  private Integer orderPrice;
  private Integer count;
}
