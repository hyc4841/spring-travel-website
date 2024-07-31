package whang.travel;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String LoggedInHome(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                               Model model) {
        if (loginMember == null) {
            return "home/home";
        }

        model.addAttribute("member", loginMember);
        return "home/home_loggedin";
    }
}
