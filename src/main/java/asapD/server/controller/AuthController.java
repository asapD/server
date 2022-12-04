package asapD.server.controller;

import asapD.server.config.security.jwt.TokenDto;
import asapD.server.controller.dto.member.MemberContactCodeRequest;
import asapD.server.controller.dto.member.MemberContactRequest;
import asapD.server.controller.dto.member.MemberEmailRequest;
import asapD.server.controller.dto.member.MemberSignInRequest;
import asapD.server.controller.dto.member.MemberSignUpRequest;
import asapD.server.repository.MemberRepository;
import asapD.server.response.BaseResponse;
import asapD.server.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    @ApiOperation(value = "회원가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<BaseResponse> signUp(
        @RequestBody @Validated MemberSignUpRequest memberSignUpRequest) {

        authService.signUp(memberSignUpRequest);

        return ResponseEntity.ok(BaseResponse.builder().message("회원가입 성공").build());
    }



    @PostMapping("/verify-email")
    @ApiOperation(value = "이메일 중복 검사", notes = "회원가입 시 이메일 증복 검사")
    @ApiResponses({
            @ApiResponse(code = 200, message = "이메일 중복 검사 성공"),
            @ApiResponse(code = 409, message = "이메일 중복")
    })
    public ResponseEntity<BaseResponse> verifyEmail(
        @RequestBody @Validated MemberEmailRequest memberEmailRequest) {

        authService.verifyEmail(memberEmailRequest);

        return ResponseEntity.ok(BaseResponse.builder().message("이메일 중복 검사 성공").build());
    }


    @PostMapping("/verify-contact")
    @ApiOperation(value = "전화번호 인증 - SMS 전송", notes = "회원가입시 휴대폰 번호 인증")
    @ApiResponses({
            @ApiResponse(code = 200, message = "전화번호 인증 요청 성공"),
            @ApiResponse(code = 500, message = "외부 api 에러")
    })
    public ResponseEntity<BaseResponse> verifyContact(
        @RequestBody @Validated MemberContactRequest memberContactRequest){

        authService.SendCertifiedMessage(memberContactRequest.getContact());

        return ResponseEntity.ok(BaseResponse.builder().message("전화번호 인증 요청 성공").build());
    }

    @PostMapping("/verify-contact-code")
    @ApiOperation(value = "전화번호 인증 - 인증코드 검증", notes = "인증코드 검증")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증 성공"),
            @ApiResponse(code = 403, message = "유효시간 만료"),
            @ApiResponse(code = 400, message = "인증코드 불일치")
    })
    public ResponseEntity<BaseResponse> verifyContactCode(
        @RequestBody @Validated MemberContactCodeRequest memberContactCodeRequest) {

        authService.verifyCode(memberContactCodeRequest);

        return ResponseEntity.ok(BaseResponse.builder().message("인증 성공").build());
    }


    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 400, message = "로그인 실패", responseHeaders = @ResponseHeader())
    })
    public ResponseEntity<BaseResponse> signIn(
        @RequestBody @Validated MemberSignInRequest memberSignInRequest) {

        String accessToken = authService.signIn(memberSignInRequest);

        return ResponseEntity.ok(BaseResponse.builder().message("로그인 성공").data(new TokenDto("Bearer "+accessToken)).build());
    }
}
