package asapD.server.controller.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberSignInRequest {

    @ApiModelProperty(example = "이메일")
    private String email;

    @ApiModelProperty(example = "비밀번호")
    private String password;

}
