package whang.travel.web.signup;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping
public class SignUpController { // 회원 가입 컨트롤러

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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

        memberRepository.save(member);
        log.info("회원가입 성공={}", member);

        return "redirect:" + redirectURL; // 회원가입 성공하면 홈 화면으로 이동.
    }

}
