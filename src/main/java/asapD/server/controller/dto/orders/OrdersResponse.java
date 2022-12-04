package asapD.server.controller.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponse {

  private Long orderId;
  private String serialNum;

}
