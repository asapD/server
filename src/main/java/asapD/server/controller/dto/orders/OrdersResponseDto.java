package asapD.server.controller.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponseDto {

  private Long ordersId;
  private String serialNum;

}
