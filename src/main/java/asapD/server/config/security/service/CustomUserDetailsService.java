//package asapD.server.config.security.service;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import solobob.solobobmate.domain.Member;
//import solobob.solobobmate.repository.MemberRepository;
//
//import java.util.Collections;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final MemberRepository userRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByLoginId(username)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다"));
//    }
//
//    private UserDetails createUserDetails(Member member) {
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());
//        return new User(member.getLoginId()
//                , member.getPassword()
//                , Collections.singleton(grantedAuthority));
//    }
//}
