package asapD.server.controller.dto.orders;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersRequestDto {

  @NotBlank
  private HashMap<Long, Integer> items;

  @NotBlank
  private String destination;
}
