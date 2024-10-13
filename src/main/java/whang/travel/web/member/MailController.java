package whang.travel.web.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whang.travel.web.member.form.MemberEmailRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailController {

    /*
    private final MailService mailService;
    @PostMapping("/mailSend")
    public String mailSend(@RequestBody @Valid MemberEmailRequest emailDto){
        log.info("이메일 인증 이메일 :" + emailDto.getEmail());
        String authNum = mailService.joinEmail(emailDto.getEmail());
        log.info("인증 번호={}", authNum);
        return authNum;
    }

     */

}
