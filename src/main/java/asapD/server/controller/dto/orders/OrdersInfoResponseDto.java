package asapD.server.controller.dto.orders;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersInfoResponseDto {

  private Long memberId;
  private List<OrderItemResponseDto> orderItemList;
  private Long deliveryId;
  private String destination;
}
