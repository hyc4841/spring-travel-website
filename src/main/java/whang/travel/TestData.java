package whang.travel;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;

@Component
@RequiredArgsConstructor
public class TestData {

    private final MemberRepository memberRepository;

    /**
     *  테스트용 데이터
     */
    @PostConstruct
    public void Init() {

        Member member = new Member();
        member.setFirstName("황");
        member.setLastName("윤철");
        member.setEmail("dbscjf4841@naver.com");
        member.setPassword("123");
        member.setMemberId("test");
        member.setAddress("서울시 은평구");
        memberRepository.save(member);

    }

}
