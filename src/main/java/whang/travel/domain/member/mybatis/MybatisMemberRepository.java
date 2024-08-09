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
        log.info("멤버 저장 성공={}", member);
        return member;
    }

    @Override
    public void update(Long id, MemberUpdateForm updateMember) {
        memberMapper.update(id, updateMember);
    }

    @Override
    public Optional<Member> findById(Long id) {
        Optional<Member> findMember = memberMapper.findById(id);
        log.info("멤버 고유 번호로 찾기={}", findMember);
        return findMember;
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        Optional<Member> findMember = memberMapper.findByLoginId(loginId);
        log.info("로그인 id로 멤버 찾기={}", findMember);
        return findMember;
    }
}
