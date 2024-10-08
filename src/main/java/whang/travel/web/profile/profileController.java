package whang.travel.web.profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import whang.travel.domain.accommodation.Accommodation;
import whang.travel.domain.accommodation.AccommodationService;
import whang.travel.domain.accommodation.mybatis.Room;
import whang.travel.domain.bulletinboard.Post;
import whang.travel.domain.bulletinboard.PostService;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.domain.paging.MemberPostCriteria;
import whang.travel.domain.paging.PageMaker;
import whang.travel.domain.paging.PageMakerMemberPost;
import whang.travel.domain.reservation.Reservation;
import whang.travel.domain.reservation.ReservationService;
import whang.travel.domain.reservation.ReservationShow;
import whang.travel.web.member.form.MemberUpdateForm;
import whang.travel.web.profile.form.NonMember;

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
    private final PasswordEncoder passwordEncoder;
    private final PostService postService;

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

    // get : 예약 내역 상세 화면
    @GetMapping("/reservations/{reservationId}")
    public String reservationDetail(@PathVariable Long reservationId,
                                    @AuthenticationPrincipal UserDetails user ,Model model) {

        Optional<Reservation> reservation = reservationService.findReservationById(reservationId);
        Room room = accommodationService.findRoomById(reservation.get().getRoom());
        Accommodation accommodation = accommodationService.findAccommoById(room.getAccommodation()).get();
        ReservationShow reservationShow = reservationService.findReservationListByReservationId(reservationId);
        Optional<Member> member = memberRepository.findById(reservation.get().getMember());

        member.ifPresent(value -> model.addAttribute("member", value));

        model.addAttribute("reservationShow", reservationShow);
        model.addAttribute("accommodation", accommodation);
        model.addAttribute("room", room);
        model.addAttribute("reservation", reservation.get());
        model.addAttribute("user", user);

        return "/profile/reservationDetail";
    }

    // get : 비회원 예약 확인 화면. 자기가 입력했던 예약인과 전화번호로 찾는다.
    @GetMapping("/reservations/non-member")
    public String nonMemberReservationView(@ModelAttribute("nonMember") NonMember nonMember) {
        return "/profile/non-member";
    }

    @PostMapping("/reservations/non-member")
    public String nonMemberReservation(@Validated @ModelAttribute("nonMember") NonMember nonMember,
                                       BindingResult bindingResult, Model model) {
        // 만약에 이용자가 입력한 정보가 틀리거나, 없는 예약 내역을 확인하려 하면 오류 페이지를 보여줘야함.
        if (bindingResult.hasErrors()) {
            // null 체크[]
            log.info("비회원 예약 조회 오류={}", bindingResult);
            return "/profile/non-member";
        }
        // 비회원 예약내역 확인 로직
        // reservation 테이블에서 검색해서

        List<Reservation> reservation = reservationService.findNonMemberReservation(nonMember);

        if (!reservation.isEmpty()) {
            for (Reservation r : reservation) {
                if (passwordEncoder.matches(nonMember.getPassword(), r.getPassword())) {
                    Room room = accommodationService.findRoomById(r.getRoom());
                    Accommodation accommodation = accommodationService.findAccommoById(room.getAccommodation()).get();
                    ReservationShow reservationShow = reservationService.findReservationListByReservationId(r.getReservationId());

                    model.addAttribute("reservationShow", reservationShow);
                    model.addAttribute("accommodation", accommodation);
                    model.addAttribute("room", room);
                    model.addAttribute("reservation", r);
                    return "/profile/non-memberReservation";
                }
            }
        }
        else {
            bindingResult.addError(new ObjectError("noReservation", "입력하신 정보로 예약 내역이 검색되지 않습니다!!"));
            log.info("조회된 비회원 예약내역이 없음={}", bindingResult);
            return "/profile/non-member";
        }

        // 여기로 넘어오는 이유는 예약내역은 있는데 비번이 안맞아서 여기로 튕기는 것
        return "redirect:/home";
    }

    // get : 내 게시물 화면 미완성
    @GetMapping("/posts")
    public String posts(@ModelAttribute("criteria") MemberPostCriteria criteria,
                        @AuthenticationPrincipal UserDetails user, Model model) {

        Long memberId = memberRepository.findIdByLoginId(user.getUsername());
        criteria.setMemberId(memberId); // memberId로 게시물 찾을 수 있도록 넣어준다.

        List<Post> postList = postService.findPostByMemberId(criteria); // 멤버의 게시물(제목 검색도 포함됨) 리스트 가져오기
        Integer total = postService.countMemberPosts(criteria); // 멤버의 총 게시물 개수(제목 검색 포함)

        PageMakerMemberPost pageMaker = new PageMakerMemberPost(criteria, total);

        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("posts", postList);
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
