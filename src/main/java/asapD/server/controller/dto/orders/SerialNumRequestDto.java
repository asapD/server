package asapD.server.controller.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerialNumRequestDto {

  @NotBlank
  private String serialNum;

  @NotBlank
  private Long orderId;
}
