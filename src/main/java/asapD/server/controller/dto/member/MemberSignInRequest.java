package asapD.server.controller.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MemberSignInRequest {

    @ApiModelProperty(example = "이메일")
    @NotBlank(message = "값이 존재하지 않습니다")
    private String email;

    @ApiModelProperty(example = "비밀번호")
    @NotBlank(message = "값이 존재하지 않습니다")
    @Pattern(regexp = "^(?=.*\\\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}")
    private String password;

}
