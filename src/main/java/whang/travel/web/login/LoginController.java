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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    @GetMapping("/login")
    public String LoginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String Login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) throws IOException {

        if (bindingResult.hasErrors()) { // 필드 오류가 있으면 입력했던 정보 가지고 다시 로그인 화면으로 감.
            log.info("BindingResult={}", bindingResult);
            return "login/loginForm";
        }
        Member loginMember = loginService.Login(loginForm.getLoginId(), loginForm.getPassword()); // 실제 로그인 로직이 돌아가는곳.

        if (loginMember == null) { // 로그인 요청 정보로 유저 찾았는데 없으면
            log.info("로그인 실패={}", loginForm);
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다."); // 글로벌 오류로 보냄
            return "login/loginForm";
        }

        // 로그인 성공
        log.info("로그인 성공={}", loginMember);
        // HttpSession에서 세션 등록해주면 이름이 JSESSION이고 값은 예측 불가능한 값을 넣어준다.
        HttpSession session = request.getSession(); // 세션이 있으면 세션을 반환하고, 세션이 없으면 새로운 세션을 만든다.
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        log.info("세션 확인={}", session);
        return "redirect:" + redirectURL; // post 요청의 경우 성공하면 redirect 시켜주는게 낫다. 새로고침하면 post 요청이 다시 갈 수 있기 때문
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 로그아웃은 세션을 제거해주면 된다.
        if (session != null) {
            session.invalidate(); // invalidate()은 세션을 지워준다.
        }

        return "redirect:/";
    }

}
