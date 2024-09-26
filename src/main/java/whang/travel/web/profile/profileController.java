package whang.travel.web.profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whang.travel.domain.accommodation.Accommodation;
import whang.travel.domain.accommodation.AccommodationService;
import whang.travel.domain.accommodation.mybatis.Room;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.domain.reservation.Reservation;
import whang.travel.domain.reservation.ReservationService;
import whang.travel.domain.reservation.ReservationShow;
import whang.travel.web.member.form.MemberUpdateForm;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class profileController {

    private final MemberRepository memberRepository;
    private final ReservationService reservationService;
    private final AccommodationService accommodationService;

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

    // post : 회원 정보 수정 미완성
    @PostMapping("/info/{id}")
    public String updateInfo(@PathVariable Long id, @ModelAttribute("member") MemberUpdateForm member) {
        memberRepository.update(id, member);
        return "redirect:/profile/info";
    }

    // get : 비번 수정 화면 미완성
    @GetMapping("/pw")
    public String pw(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", user);
        return "/profile/pw";
    }

    // get : 예약 내역 리스트 화면
    @GetMapping("/reservations")
    public String reservations(@AuthenticationPrincipal UserDetails user, Model model) {
        Member member = memberRepository.findByLoginId(user.getUsername()).get();
        List<ReservationShow> reservationList = reservationService.findReservationListByMemberId(member.getId());

        log.info(String.valueOf(reservationList.isEmpty()));
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("member", member);
        model.addAttribute("user", user);
        return "/profile/reservations";
    }

    // get : 예약 내역 상세 화면 미완성
    @GetMapping("/reservations/{reservationId}")
    public String reservationDetail(@PathVariable Long reservationId,
                                    @AuthenticationPrincipal UserDetails user ,Model model) {
        Reservation reservation = reservationService.findReservationById(reservationId); // Optional 걸어야하는지 알아보기
        Room room = accommodationService.findRoomById(reservation.getRoom());
        Accommodation accommodation = accommodationService.findAccommoById(room.getAccommodation()).get();
        ReservationShow reservationShow = reservationService.findReservationListByReservationId(reservationId);
        Optional<Member> member = memberRepository.findById(reservation.getMember());

        member.ifPresent(value -> model.addAttribute("member", value));

        model.addAttribute("reservationShow", reservationShow);
        model.addAttribute("accommodation", accommodation);
        model.addAttribute("room", room);
        model.addAttribute("reservation", reservation);
        model.addAttribute("user", user);

        return "/profile/reservationDetail";
    }

    // get : 내 게시물 화면 미완성
    @GetMapping("/posts")
    public String posts(@AuthenticationPrincipal UserDetails user, Model model) {

        model.addAttribute("user", user);
        return "/profile/posts";
    }

    // get : 내 쿠폰 화면 미완성
    @GetMapping("/coupon")
    public String coupon(@AuthenticationPrincipal UserDetails user, Model model) {

        model.addAttribute("user", user);
        return "/profile/coupon";
    }

    // get : 설정 화면 미완성
    @GetMapping("/settings")
    public String settings(@AuthenticationPrincipal UserDetails user, Model model) {

        model.addAttribute("user", user);

        return "/profile/setting";
    }





}
