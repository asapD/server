package asapD.server.controller;

import asapD.server.controller.dto.member.*;
import asapD.server.controller.exception.ApiException;
import asapD.server.controller.exception.ApiExceptionEnum;
import asapD.server.repository.MemberRepository;
import asapD.server.response.ExceptionResponse;
import asapD.server.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import io.swagger.v3.oas.annotations.Parameter;
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


    @PostMapping("/sign-up")
    @ApiOperation(value = "회원가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public void signUp(@Parameter(name = "사용자 정보 입력") @RequestBody MemberSignUpRequest memberSignUpRequest) {
        authService.signUp(memberSignUpRequest);
    }


    @PostMapping("/verify-email")
    @ApiOperation(value = "이메일 중복 검사", notes = "회원가입 시 이메일 증복 검사")
    @ApiResponses({
            @ApiResponse(code = 200, message = "이메일 중복 X"),
            @ApiResponse(code = 409, message = "이메일 중복")
    })
    public void verifyEmail(@RequestBody MemberEmailRequest memberEmailRequest) {
        if (memberRepository.findByEmail(memberEmailRequest.getEmail()).isPresent()) {
            throw new ApiException(ApiExceptionEnum.DUPLICATION_VALUE_EXCEPTION);
        }
    }


    @PostMapping("/verify-contact")
    @ApiOperation(value = "전화번호 인증 - SMS 전송", notes = "회원가입시 휴대폰 번호 인증")
    @ApiResponses({
            @ApiResponse(code = 200, message = "정상 처리"),
            @ApiResponse(code = 500, message = "외부 api 에러")
    })
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
    @ApiOperation(value = "전화번호 인증 - 인증코드 검증", notes = "인증코드 검증")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증 성공"),
            @ApiResponse(code = 403, message = "유효시간 만료"),
            @ApiResponse(code = 400, message = "인증코드 불일치")
    })
    public void verifyContactCode(@RequestBody MemberContactCodeRequest memberContactCodeRequest) {
        authService.verifyCode(memberContactCodeRequest);
    }


    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 400, message = "로그인 실패", responseHeaders = @ResponseHeader())
    })
    public void signIn(@RequestBody MemberSignInRequest memberSignInRequest, HttpServletResponse response) {
        String accessToken = authService.signIn(memberSignInRequest);
        response.addHeader("Authorization", "Bearer "+ accessToken);
    }
}
