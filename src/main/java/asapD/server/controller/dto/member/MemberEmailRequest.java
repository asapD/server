package asapD.server.controller.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.MessageInterpolator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberEmailRequest {

    @ApiModelProperty(example = "이메일")
    @NotBlank(message = "값이 존재하지 않습니다")
    @Email(message = "이메일 형식에 맞지 않습니다")
    private String email;

}
