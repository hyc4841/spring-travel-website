package whang.travel.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository { // 회원 저장소가 해야하는 기능

    // 회원 저장
    void save(Member member);

    // 회원 조회
    Member findById(Long id);

    // 모든 회원 찾기
    List<Member> findAll();

    // 로그인 id로 회원 찾기
    Optional<Member> findByLoginId(String loginId);



}
