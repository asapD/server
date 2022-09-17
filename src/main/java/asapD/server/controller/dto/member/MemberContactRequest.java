package asapD.server.controller.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberContactRequest {

    @ApiModelProperty(example = "전화번호")
    @NotBlank(message = "값이 존재하지 않습니다")
    private String contact;
}
