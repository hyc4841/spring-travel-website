package whang.travel.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.web.member.form.MemberUpdateForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class MemoryMemberRepositoryImpl implements MemberRepository {

    private static Map<Long, Member> store = new ConcurrentHashMap<>(); // 동시성 문제 때문에 ConcurrentHashMap 사용 일단 테스트
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        log.info("멤버 저장 : member={}", member);

        return member;
    }

    @Override
    public void update(Long id, MemberUpdateForm updateMember) {
        Member findMember = store.get(id);
        findMember.setFirstName(updateMember.getFirstName());
        findMember.setLastName(updateMember.getLastName());
        findMember.setMemberId(updateMember.getMemberId());
        findMember.setEmail(updateMember.getEmail());
        findMember.setAddress(updateMember.getAddress());
        findMember.setAddress(updateMember.getPassword());
    }


    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        // 람다식 사용
        return findAll().stream()
                .filter(m -> m.getMemberId().equals(loginId))
                .findFirst();
    }

    @Override
    public Long findIdByLoginId(String loginId) {
        return null;
    }
}
