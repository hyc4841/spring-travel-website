package whang.travel.domain.member;

import java.util.Optional;

public interface MemberService {

    // 회원 가입
    void join(Member member);

    // 회원 조회
    Optional<Member> findMember(Long id);
}
