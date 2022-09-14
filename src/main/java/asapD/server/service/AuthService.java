package asapD.server.service;

import asapD.server.config.security.jwt.TokenProvider;
import asapD.server.controller.dto.member.MemberSignInRequest;
import asapD.server.controller.dto.member.MemberSignUpRequest;
import asapD.server.domain.Authority;
import asapD.server.domain.Member;
import asapD.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;


// 이메일 중복 , 전화번호 인증
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

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
    public void SendCertifiedMessage(String phoneNumber, String cerNum){
        String api_key = "NCSK0TRMGX0QQC86";
        String api_secret = "RVDMYMYTYNFI0FKDDVKBF3P3XIMLCGX7";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);
        params.put("from", "01086094105");
        params.put("type", "SMS");
        params.put("text", "asapD : 인증번호는" + "["+cerNum+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
