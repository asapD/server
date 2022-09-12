package asapD.server.controller.dto.member;

import lombok.Data;

@Data
public class MemberSignUpRequest {

    private String name;

    private String contact;

    private String email;

    private String password;
}
