package asapD.server.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

  @ApiModelProperty(example = "에러코드")
  private Integer status;

  @ApiModelProperty(example = "에러메시지")
  private String message;
}
