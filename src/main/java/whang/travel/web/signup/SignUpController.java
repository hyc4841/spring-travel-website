package whang.travel.web.signup;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.web.login.form.LoginForm;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping
public class SignUpController { // 회원 가입 컨트롤러

    private final MemberRepository memberRepository;

    // get : 회원 가입 폼 이동. 지금 로그인 화면하고 회원가입 화면이 합쳐져 있음
    @GetMapping("/signup/add")
    public String signupForm(@ModelAttribute("member") Member member) {
        return "login/checkout";
    }

    // post : 회원 가입
    @ResponseBody
    @PostMapping("/signup/add")
    public String signup(@RequestBody Member member, BindingResult bindingResult, HttpServletResponse response, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute(member);
            response.sendRedirect("/signup/add/test");
            return "Bad Request";
        }
        memberRepository.save(member);
        response.sendRedirect("/login/loginFormTest");
        return "ok"; // 회원가입 성공하면 홈 화면으로 이동.
    }

    @GetMapping("/login/loginFormTest")
    public String testForm() {
        return "login/loginFormTest";
    }

}
