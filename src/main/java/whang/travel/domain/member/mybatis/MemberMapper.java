package whang.travel.domain.member.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import whang.travel.domain.member.Member;
import whang.travel.web.member.form.MemberUpdateForm;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(Member member);

    void update(@Param("id") Long id, @Param("updateMember") MemberUpdateForm updateForm);

    Optional<Member> findById(Long id);

    Optional<Member> findByLoginId(String loginId);

    List<Member> findAll();

    Long findIdByLoginId(String loginId);
}
