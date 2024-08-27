package whang.travel.domain.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.domain.member.MemberService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSecurityService implements UserDetailsService {
    // 여기는 로그인 할 때 유저 꺼내서 확인하기

    private final MemberRepository memberRepository;

    // 여기가 실제로 데이터베이스에서 유저 조회해서 패스워드 비교하는 부분
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("입력한 username={}", username);
        // 데이터베이스에서 조회. 여기서 받는건 username이라고 하지만 실제로 유니크한 멤버의 로그인id나 다른 유니크한 값을 써야함. 중복되면 안되니까
        Optional<Member> member = memberRepository.findByLoginId(username);
        log.info("멤버 찾기 실행={}", member);

        if (member.isEmpty()) {
            throw new UsernameNotFoundException("해당 유저가 존재하지 않습니다: " + username);
        }

        return User.withUsername(member.get().getMemberId())
                .password(member.get().getPassword())
                .roles(member.get().getRole()) // 역할(등급) 부여
                .build();
    }
}
