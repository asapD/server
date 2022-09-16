package asapD.server.controller.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberContactRequest {

    @ApiModelProperty(example = "전화번호")
    private String contact;
}
