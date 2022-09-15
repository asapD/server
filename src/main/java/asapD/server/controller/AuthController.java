package asapD.server.controller;

import asapD.server.controller.dto.member.*;
import asapD.server.controller.exception.ApiException;
import asapD.server.controller.exception.ApiExceptionEnum;
import asapD.server.repository.MemberRepository;
import asapD.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     *
     * @param memberSignUpRequest
     */
    @PostMapping("/sign-up")
    public void signUp(@RequestBody MemberSignUpRequest memberSignUpRequest) {
        authService.signUp(memberSignUpRequest);
    }

    /**
     * 이메일 중복 검사
     *
     * @param memberEmailRequest
     */
    @PostMapping("/verify-email")
    public void verifyEmail(@RequestBody MemberEmailRequest memberEmailRequest) {
        if (memberRepository.findByEmail(memberEmailRequest.getEmail()).isPresent()) {
            throw new ApiException(ApiExceptionEnum.DUPLICATION_VALUE_EXCEPTION);
        }
    }

    /**
     * 전화번호 인증
     *
     * @param memberContactRequest
     */
    @PostMapping("/verify-contact")
    public void verifyContact(@RequestBody MemberContactRequest memberContactRequest){
        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        authService.SendCertifiedMessage(memberContactRequest.getContact(), numStr);
    }

    @PostMapping("/verify-contact-code")
    public void verifyContactCode(@RequestBody MemberContactCodeRequest memberContactCodeRequest) {
        authService.verifyCode(memberContactCodeRequest);
    }

    /**
     * 로그인
     * @param memberSignInRequest
     * @param response
     * return header:{"Authorization" : "Bearer {access token}"}
     */
    @PostMapping("/sign-in")
    public void signIn(@RequestBody MemberSignInRequest memberSignInRequest, HttpServletResponse response) {
        String accessToken = authService.signIn(memberSignInRequest);
        response.addHeader("Authorization", "Bearer "+ accessToken);
    }
}
