package asapD.server.controller.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerialNumRequest {

  private String serialNum;
  private Long orderId;

}
