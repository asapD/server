package asapD.server.service;

import asapD.server.config.security.jwt.TokenProvider;
import asapD.server.controller.dto.member.MemberContactCodeRequest;
import asapD.server.controller.dto.member.MemberEmailRequest;
import asapD.server.controller.dto.member.MemberSignInRequest;
import asapD.server.controller.dto.member.MemberSignUpRequest;
import asapD.server.controller.exception.ApiException;
import asapD.server.controller.exception.ApiExceptionEnum;
import asapD.server.domain.Authority;
import asapD.server.domain.Member;
import asapD.server.repository.MemberRepository;
import asapD.server.utils.RedisClient;
import asapD.server.utils.SmsClient;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;


// 이메일 중복 , 전화번호 인증
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;
    private final RedisClient redisClient;
    private final SmsClient smsClient;

    /**
     * 회원가입
     * @param memberSignUpRequest
     */
    public void signUp(MemberSignUpRequest memberSignUpRequest) {

        Member member = Member.builder()
                .name(memberSignUpRequest.getName())
                .email(memberSignUpRequest.getEmail())
                .password(passwordEncoder.encode(memberSignUpRequest.getPassword()))
                .contact(memberSignUpRequest.getContact())
                .authority(Authority.ROLE_USER)
                .build();
        memberRepository.save(member);
    }

    /**
     * 로그인
     * @param memberSignInRequest
     * @return token
     */
    public String signIn(MemberSignInRequest memberSignInRequest) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(memberSignInRequest.getEmail(), memberSignInRequest.getPassword());
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authenticate);
    }


    /**
     * 전화번호 인증
     *
     */
    public void SendCertifiedMessage(String phoneNumber){
        String code = smsClient.createRandomNum();
        verifiedCodeSave(phoneNumber, code);
        smsClient.sendMessage(phoneNumber, code);
    }

    public void verifiedCodeSave(String contact, String code) {
        redisClient.setValue(contact, code, 3L); // 유효시간 : 3분
    }

    public void verifyCode(MemberContactCodeRequest memberContactCodeRequest) {

        String findCode = Optional.ofNullable(redisClient.getValue(memberContactCodeRequest.getContact())).orElseThrow(
                () -> new ApiException(ApiExceptionEnum.TIMEOUT_EXCEPTION)
        );
        if (!findCode.equals(memberContactCodeRequest.getCode())) {
            throw new ApiException(ApiExceptionEnum.SERIALNUM_INVALID_EXCEPTION);
        }

    }

    public void verifyEmail(MemberEmailRequest memberEmailRequest) {
        if (memberRepository.findByEmail(memberEmailRequest.getEmail()).isPresent()) {
            throw new ApiException(ApiExceptionEnum.DUPLICATION_VALUE_EXCEPTION);
        }
    }
}
