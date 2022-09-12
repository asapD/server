package asapD.server.service;

import asapD.server.config.security.jwt.TokenProvider;
import asapD.server.controller.dto.member.MemberSignInRequest;
import asapD.server.controller.dto.member.MemberSignUpRequest;
import asapD.server.domain.Authority;
import asapD.server.domain.Member;
import asapD.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
}
