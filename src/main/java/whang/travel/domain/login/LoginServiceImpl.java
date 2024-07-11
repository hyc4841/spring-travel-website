package whang.travel.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;

    @Override
    public Member Login(String loginId, String password) {

        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null); // null이 리턴 되면 로그인 실패
    }
}
