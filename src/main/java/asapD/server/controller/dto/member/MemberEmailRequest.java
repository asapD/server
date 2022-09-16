package asapD.server.controller.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberEmailRequest {

    @ApiModelProperty(example = "이메일")
    private String email;

}
