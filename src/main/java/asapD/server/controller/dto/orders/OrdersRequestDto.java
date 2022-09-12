package asapD.server.controller.dto.orders;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersRequestDto {

  private HashMap<Long, Integer> items;
  private String destination;
}
