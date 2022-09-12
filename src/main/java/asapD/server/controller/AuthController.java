package asapD.server.controller;

import asapD.server.controller.dto.member.MemberSignInRequest;
import asapD.server.controller.dto.member.MemberSignUpRequest;
import asapD.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody MemberSignUpRequest memberSignUpRequest) {
        authService.signUp(memberSignUpRequest);
    }

    @PostMapping("/sign-in")
    public void signIn(@RequestBody MemberSignInRequest memberSignInRequest, HttpServletResponse response) {
        String accessToken = authService.signIn(memberSignInRequest);
        response.addHeader("Authorization", "Bearer "+ accessToken);
    }
}
