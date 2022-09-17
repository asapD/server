package asapD.server.controller.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberContactCodeRequest {

    @ApiModelProperty(example = "전화번호")
    private String contact;

    @ApiModelProperty(example = "인증코드")
    private String code;
}
