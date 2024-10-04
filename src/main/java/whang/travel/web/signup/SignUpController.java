package whang.travel.web.signup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import whang.travel.config.MemberSignupValidator;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.web.signup.form.Sex;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping
public class SignUpController { // 회원 가입 컨트롤러

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberSignupValidator memberValidator;

    @ModelAttribute("sex")
    public Sex[] sex() {
        return Sex.values();
    }

    // get : 회원 가입 폼 이동. 지금 로그인 화면하고 회원가입 화면이 합쳐져 있음
    @GetMapping("/signup/add")
    public String signupForm(@ModelAttribute("member") Member member) {
        return "/signup/signup";
    }

    // post : 회원 가입
    @PostMapping("/signup/add")
    public String signup(@Validated @ModelAttribute("member") Member member,
                         BindingResult bindingResult,
                         @RequestParam(defaultValue = "/home") String redirectURL)  {

        /*
        // 이메일 검증
        boolean emailValidation = EmailValidator.getInstance().isValid(member.getEmail());
        log.info("이메일이 유효한지 검사={}", emailValidation);
        if (!emailValidation) {
            bindingResult.addError(new FieldError("member", "email", "유효한 이메일 형식이 아닙니다!!"));
        }

        // 가입하려는 유저가 입력한 memberId값이 이미 다른 사람이 사용중이면 오류로 보여준.
        Optional<Member> checkMember = memberRepository.findByLoginId(member.getMemberId());
        if (!checkMember.isEmpty()) {
            log.info("멤버 id 중복 발생={}", checkMember);
            // objectName : ModelAttribute이름, field : 오류가 난 필드 이름, 메세지는 메시지
            bindingResult.addError(new FieldError("member", "memberId", "이미 사용 중인 id입니다!!"));
        }
         */

        List<FieldError> validation = memberValidator.validation(member, bindingResult);
        log.info("오류 검증 결과={}", validation);
        for (FieldError error : validation) {
            bindingResult.addError(error);
        }

        // 회원가입 데이터에 대한 검증
        if (bindingResult.hasErrors()) {
            log.info("오류 있음={}", bindingResult);
            return "signup/signup";
        }

        // 회원가입할 때 오류 : 아이디 중복, 비밀번호 확인 불일치, 유효한 이메일이 아님, 필수 필드를 입력하지 않음. 등등...
        // 이메일 양식, 아이디에 특수문자 사용불가, 각 필드 길이 제한 등 검증 부분 추가적으로 업데이트 해야함

        // 회원 가입할 때 비밀번호를 암호화 한 후에 데이터베이스에 넣는다.
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        // 멤버 등급 부여, 여기선 일반 회원 등급인 user를 부여
        member.setRole("user");

        memberRepository.save(member);

        return "redirect:" + redirectURL; // 회원가입 성공하면 홈 화면으로 이동.
    }

}
