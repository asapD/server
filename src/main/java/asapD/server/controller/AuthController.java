package asapD.server.controller;

import asapD.server.controller.dto.member.*;
import asapD.server.controller.exception.ApiException;
import asapD.server.controller.exception.ApiExceptionEnum;
import asapD.server.repository.MemberRepository;
import asapD.server.response.BaseResponse;
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
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final MemberRepository memberRepository;


    @PostMapping("/sign-up")
    @ApiOperation(value = "회원가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<BaseResponse> signUp(@RequestBody MemberSignUpRequest memberSignUpRequest) {
        authService.signUp(memberSignUpRequest);
        return ResponseEntity.ok(BaseResponse.builder().message("회원가입 성공").build());
    }


    @PostMapping("/verify-email")
    @ApiOperation(value = "이메일 중복 검사", notes = "회원가입 시 이메일 증복 검사")
    @ApiResponses({
            @ApiResponse(code = 200, message = "이메일 중복 검사 성공"),
            @ApiResponse(code = 409, message = "이메일 중복")
    })
    public ResponseEntity<BaseResponse> verifyEmail(@RequestBody MemberEmailRequest memberEmailRequest) {
        authService.verifyEmail(memberEmailRequest);
        return ResponseEntity.ok(BaseResponse.builder().message("이메일 중복 검사 성공").build());
    }


    @PostMapping("/verify-contact")
    @ApiOperation(value = "전화번호 인증 - SMS 전송", notes = "회원가입시 휴대폰 번호 인증")
    @ApiResponses({
            @ApiResponse(code = 200, message = "전화번호 인증 요청 성공"),
            @ApiResponse(code = 500, message = "외부 api 에러")
    })
    public ResponseEntity<BaseResponse> verifyContact(@RequestBody MemberContactRequest memberContactRequest){
        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        authService.SendCertifiedMessage(memberContactRequest.getContact(), numStr);
        return ResponseEntity.ok(BaseResponse.builder().message("전화번호 인증 요청 성공").build());
    }

    @PostMapping("/verify-contact-code")
    @ApiOperation(value = "전화번호 인증 - 인증코드 검증", notes = "인증코드 검증")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증 성공"),
            @ApiResponse(code = 403, message = "유효시간 만료"),
            @ApiResponse(code = 400, message = "인증코드 불일치")
    })
    public ResponseEntity<BaseResponse> verifyContactCode(@RequestBody MemberContactCodeRequest memberContactCodeRequest) {
        authService.verifyCode(memberContactCodeRequest);
        return ResponseEntity.ok(BaseResponse.builder().message("인증 성공").build());
    }


    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 400, message = "로그인 실패", responseHeaders = @ResponseHeader())
    })
    public ResponseEntity<BaseResponse> signIn(@RequestBody MemberSignInRequest memberSignInRequest, HttpServletResponse response) {
        String accessToken = authService.signIn(memberSignInRequest);
        response.addHeader("Authorization", "Bearer "+ accessToken);
        return ResponseEntity.ok(BaseResponse.builder().message("로그인 성공").build());
    }
}
