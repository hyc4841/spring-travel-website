package whang.travel.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberSignupValidator {

    private final MemberRepository memberRepository;

    public List<FieldError> validation(Member member, BindingResult bindingResult) {
        ArrayList<FieldError> fieldErrors = new ArrayList<>();

        // 이메일 검증
        boolean emailVaildation = EmailValidator.getInstance().isValid(member.getEmail());
        log.info("이메일 검증 값={}", emailVaildation);

        if (!emailVaildation) {
            fieldErrors.add(new FieldError("member", "email", "유효한 이메일 형식이 아닙니다!!"));
            // bindingResult.addError(new FieldError("member", "email", "유효한 이메일 형식이 아닙니다!!"));
        }

        // 멤버 중복 검사
        Optional<Member> findMember = memberRepository.findByLoginId(member.getMemberId());
        if (!findMember.isEmpty()) {
            fieldErrors.add(new FieldError("member", "memberId", "이미 사용 중인 id입니다!!"));
//            bindingResult.addError(new FieldError("member", "memberId", "이미 사용 중인 id입니다!!"));
        }

        return fieldErrors;
    }
}
