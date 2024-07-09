package whang.travel.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor // 생성자 자동 생성
public class LoginController {

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
