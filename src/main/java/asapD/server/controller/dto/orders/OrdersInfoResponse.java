package asapD.server.controller.dto.orders;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersInfoResponse {

  private long memberId;
  private List<OrderItemResponse> orderItemList;
  private long deliveryId;
  private String destination;
}
