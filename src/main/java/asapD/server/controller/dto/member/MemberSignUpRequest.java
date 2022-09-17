package asapD.server.controller.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MemberSignUpRequest {

    @ApiModelProperty(example = "이름")
    @NotBlank(message = "값이 존재하지 않습니다")
    private String name;

    @ApiModelProperty(example = "전화번호")
    @NotBlank(message = "값이 존재하지 않습니다")
    private String contact;

    @ApiModelProperty(example = "이메일")
    @NotBlank(message = "값이 존재하지 않습니다")
    @Email(message = "이메일 형식에 맞지 않습니다")
    private String email;

    @ApiModelProperty(example = "비밀번호")
    @NotBlank(message = "값이 존재하지 않습니다")
    @Pattern(regexp = "^(?=.*\\\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}")
    private String password;
}
