package whang.travel.web.login;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import whang.travel.domain.login.LoginService;
import whang.travel.domain.member.Member;
import whang.travel.web.SessionConst;
import whang.travel.web.login.form.LoginForm;
import whang.travel.web.session.SessionManager;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor // 생성자 자동 생성
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String LoginForm(@ModelAttribute("loginForm") LoginForm loginForm, @ModelAttribute("member") Member member) {
        return "login/login-signup";
    }

    @ResponseBody
    @PostMapping("/login")
    public String Login(@RequestBody LoginForm Form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        log.info("loginId={}, password={}", Form.getLoginId(), Form.getPassword());
        if (bindingResult.hasErrors()) {
            model.addAttribute(Form);
            return "Bad Request";
        }
        Member loginMember = loginService.Login(Form.getLoginId(), Form.getPassword());
        if (loginMember == null) {
            log.info("로그인 실패");
            model.addAttribute(Form);
            response.sendRedirect("/login");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "Bad Request";
        }

        // 로그인 성공
        log.info("로그인 성공");
        HttpSession session = request.getSession(); // 세션이 있으면 세션을 반환하고, 세션이 없으면 새로운 세션을 만든다.
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        response.sendRedirect(redirectURL);
        return "ok";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);// 로그아웃은 세션을 제거해주면 된다.
        if (session != null) {
            session.invalidate(); // invalidate()은 세션을 지워준다.
        }

        return "redirect:/";
    }



    @GetMapping("/test")
    public String LoginFormTest() {
        return "login/loginFormTest"; // /templates/login/loginFormTest.html
    }

    @GetMapping("/test2")
    public String LoginFormTest2() {
        return "login/loginFormTest2";
    }

    @GetMapping("/test3")
    public String LoginFormTest3()
    {
        return "login/loginFormTest3";
    }
}
