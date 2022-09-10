package asapD.server.controller.dto.member;

import lombok.Data;

@Data
public class MemberSignInRequest {

    private String email;

    private String password;

}
