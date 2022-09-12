package asapD.server.config.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * authentication 의 memberId 값을 가져오는 클래스
 */
@Slf4j
public class SecurityUtil {

    /**
     * 불필요한 객체 생성 방지
     */
    private SecurityUtil(){

    }

    public static String getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("인증 정보가 없습니다.");
        }

        return authentication.getName();
    }
}
