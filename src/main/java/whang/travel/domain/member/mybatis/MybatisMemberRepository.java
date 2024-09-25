package whang.travel.domain.member.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.web.member.form.MemberUpdateForm;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisMemberRepository implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public Member save(Member member) {
        memberMapper.save(member);
        log.info("MemberRepository.save()={}", member);
        return member;
    }

    @Override
    public void update(Long id, MemberUpdateForm updateMember) {
        log.info("MemberRepository.update()={} {}", id, updateMember);
        memberMapper.update(id, updateMember);
    }

    @Override
    public void updatePw(Long id, String pw) {
        log.info("MemberRepository.updatePw");
        memberMapper.updatePw(id, pw);
    }

    // 식별 id로 멤버 찾기
    @Override
    public Optional<Member> findById(Long id) {
        Optional<Member> findMember = memberMapper.findById(id);
        log.info("MemberRepository.findById={} {}", id, findMember);
        return findMember;
    }

    // 모든 멤버 찾기
    @Override
    public List<Member> findAll() {
        List<Member> members = memberMapper.findAll();
        log.info("MemberRepository.findAll()={}", members);
        return members;
    }

    // 로그인 Id로 멤버 찾기
    @Override
    public Optional<Member> findByLoginId(String loginId) {
        Optional<Member> findMember = memberMapper.findByLoginId(loginId);
        log.info("MemberRepository.findByLoginId={} {}", loginId, findMember);
        return findMember;
    }

    @Override
    public Long findIdByLoginId(String loginId) {
        Long id = memberMapper.findIdByLoginId(loginId);
        log.info("MemberRepository.findIdByLoginId={} {}", loginId, id);
        return id;
    }
}
