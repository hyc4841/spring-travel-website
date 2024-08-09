package whang.travel.domain.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.web.member.form.MemberUpdateForm;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        // given
        Member member = new Member("김", "아아", "123", "123", "123", "123");

        // when
        Member saveMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(saveMember.getId()).get();
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void update() {
        // given
        Member member = new Member("박", "아아", "123", "123", "123", "123");
        Member saveMember = memberRepository.save(member);

        // when
        MemberUpdateForm updateMember = new MemberUpdateForm("구구", "aa", "312", "1313", "123123", "10023");
        memberRepository.update(saveMember.getId(), updateMember);

        // then
        Member findMember = memberRepository.findById(saveMember.getId()).get();
        assertThat(findMember.getFirstName()).isEqualTo(updateMember.getFirstName());
        assertThat(findMember.getLastName()).isEqualTo(updateMember.getLastName());
        assertThat(findMember.getMemberId()).isEqualTo(updateMember.getMemberId());
        assertThat(findMember.getEmail()).isEqualTo(updateMember.getEmail());
        assertThat(findMember.getAddress()).isEqualTo(updateMember.getAddress());
        assertThat(findMember.getPassword()).isEqualTo(updateMember.getPassword());
    }

    @Test
    void findById() {
        // given
        Member member1 = new Member("박", "아아", "123", "123", "123", "123");
        Member member2 = new Member("금", "ㅁㅁ", "1ㅇ3", "1213", "1323", "1243");
        Member member3 = new Member("박ㅌ", "아ㅍ아", "1ㅁ23", "1223", "1ㅎ23", "1232");

        Member saveMember1 = memberRepository.save(member1);
        Member saveMember2 = memberRepository.save(member2);
        Member saveMember3 = memberRepository.save(member3);

        // when
        Member findMember1 = memberRepository.findById(saveMember1.getId()).get();
        Member findMember2 = memberRepository.findById(saveMember2.getId()).get();
        Member findMember3 = memberRepository.findById(saveMember3.getId()).get();

        // then
        assertThat(findMember1).isEqualTo(saveMember1);
        assertThat(findMember2).isEqualTo(saveMember2);
        assertThat(findMember3).isEqualTo(saveMember3);
        assertThat(findMember3).isNotEqualTo(saveMember1);

    }

    @Test
    void findByLoginId() {
        // given
        Member member1 = new Member("박", "아아", "123", "123", "123", "123");
        Member member2 = new Member("금", "ㅁㅁ", "1ㅇ3", "1213", "1323", "1243");
        Member member3 = new Member("박ㅌ", "아ㅍ아", "1ㅁ23", "1223", "1ㅎ23", "1232");

        Member saveMember1 = memberRepository.save(member1);
        Member saveMember2 = memberRepository.save(member2);
        Member saveMember3 = memberRepository.save(member3);
        // when
        Member findMember1 = memberRepository.findByLoginId(saveMember1.getMemberId()).get();
        Member findMember2 = memberRepository.findByLoginId(saveMember2.getMemberId()).get();
        Member findMember3 = memberRepository.findByLoginId(saveMember3.getMemberId()).get();

        // then
        assertThat(findMember1).isEqualTo(saveMember1);
        assertThat(findMember2).isEqualTo(saveMember2);
        assertThat(findMember3).isEqualTo(saveMember3);
        assertThat(findMember1).isNotEqualTo(saveMember2);
    }


}
