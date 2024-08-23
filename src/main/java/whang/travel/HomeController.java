package whang.travel;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import whang.travel.domain.member.Member;
import whang.travel.web.SessionConst;

@Slf4j
@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String GoToHome() {

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String LoggedInHome(HttpServletRequest request, Model model, @AuthenticationPrincipal UserDetails user) {

        log.info("세션 확인={}", request.getSession());

        log.info("유저 확인={}", user);
        if (user != null) {
            log.info("자격확인?={}", user.getAuthorities());
        }

        model.addAttribute("user", user);


        /* spring security 도입하면서 사용 안하게 됨.
        // 로그인 안되어 있으면 기본 홈페이지로 이동
        if (loginMember == null) {
            model.addAttribute("member", null);
            return "home/homepage";
        }

        // 로그인 되어 있으면
        model.addAttribute("member", loginMember);
         */
        return "home/homepage";
    }
}
