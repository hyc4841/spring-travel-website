package whang.travel.web.profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.web.member.form.MemberUpdateForm;

@Slf4j
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class profileController {

    private final MemberRepository memberRepository;

    // get : 회원 정보 화면
    @GetMapping("/info")
    public String info(@AuthenticationPrincipal UserDetails user, Model model) {
        Member member = memberRepository.findByLoginId(user.getUsername()).get();
        model.addAttribute("user", user);
        model.addAttribute("member", member);
        return "/profile/info";
    }

    // get : 회원 정보 수정 화면
    @GetMapping("/info/{id}")
    public String updateInfoView(@PathVariable Long id, @AuthenticationPrincipal UserDetails user, Model model) {
        Member member = memberRepository.findById(id).get();
        model.addAttribute("member", member);
        model.addAttribute("user", user);
        return "/profile/updateInfo";
    }

    // post : 회원 정보 수정
    @PostMapping("/info/{id}")
    public String updateInfo(@PathVariable Long id, @ModelAttribute("member") MemberUpdateForm member) {
        memberRepository.update(id, member);
        return "redirect:/profile/info";
    }

    // get : 비번 수정 화면
    @GetMapping("/pw")
    public String pw(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", user);
        return "/profile/pw";
    }

    // get : 예약 내역 화면
    @GetMapping("/reservations")
    public String reservations(@ModelAttribute("member") Member member, @AuthenticationPrincipal UserDetails user,
                               Model model) {

        member = memberRepository.findByLoginId(user.getUsername()).get();

        model.addAttribute("user", user);

        return "/profile/reservations";
    }

//    @GetMapping("/reservations/{}")

    @GetMapping("/posts")
    public String posts(@ModelAttribute("member") Member member, @AuthenticationPrincipal UserDetails user,
                        Model model) {

        member = memberRepository.findByLoginId(user.getUsername()).get();

        model.addAttribute("user", user);
        return "/profile/posts";
    }

    @GetMapping("/coupon")
    public String coupon(@ModelAttribute("member") Member member, @AuthenticationPrincipal UserDetails user,
                         Model model) {

        member = memberRepository.findByLoginId(user.getUsername()).get();

        model.addAttribute("user", user);
        return "/profile/coupon";
    }

    @GetMapping("/settings")
    public String settings(@ModelAttribute("member") Member member, @AuthenticationPrincipal UserDetails user,
                           Model model) {

        member = memberRepository.findByLoginId(user.getUsername()).get();

        model.addAttribute("user", user);

        return "/profile/setting";
    }





}
