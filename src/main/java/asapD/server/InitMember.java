package asapD.server;

import asapD.server.domain.Authority;
import asapD.server.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init() {
        initMemberService.init();
    }

    @Component
    @RequiredArgsConstructor
    static class InitMemberService{

        private final EntityManager em;
        private final PasswordEncoder passwordEncoder;

        @Transactional
        public void init() {
            Member member = Member.builder()
                    .name("test")
                    .email("test@test")
                    .password(passwordEncoder.encode("test12345"))
                    .contact("010-1111-1111")
                    .authority(Authority.ROLE_USER)
                    .build();
            em.persist(member);
        }
    }
}
