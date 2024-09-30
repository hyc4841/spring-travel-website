package whang.travel.web.reservation;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import whang.travel.domain.accommodation.Accommodation;
import whang.travel.domain.accommodation.AccommodationService;
import whang.travel.domain.accommodation.mybatis.Room;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.domain.reservation.Reservation;
import whang.travel.domain.reservation.ReservationNonMember;
import whang.travel.domain.reservation.ReservationService;
import whang.travel.web.reservation.form.VisitType;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Controller
public class ReservationController {

    private final MemberRepository memberRepository;
    private final ReservationService reservationService;
    private final AccommodationService accommodationService;
    private final PasswordEncoder passwordEncoder;

    private IamportClient iamportClient;

    @Value("${imp.api.key}")
    private String apiKey;
    @Value("${imp.api.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }

    @ModelAttribute("visitType")
    public VisitType[] visitTypes() {
        return VisitType.values();
    }

    // get : 예약 화면
    @GetMapping("/checkout/{roomId}")
    public String reservationView(@PathVariable Long roomId, @ModelAttribute("reservation") Reservation reservation,
                                  Model model, @AuthenticationPrincipal UserDetails user) {

        Room room = accommodationService.findRoomById(roomId);
        Accommodation accommodation = accommodationService.findAccommoById(room.getAccommodation()).get();

        model.addAttribute("user", user);
        model.addAttribute("accommodation", accommodation);
        model.addAttribute("room", room);

        if (user != null) { // 현재 로그인한 유저가 있으면. 로그인 유저의 정보 가져와서 번호 뿌려주기
            // 회원 예약
            Optional<Member> member = memberRepository.findByLoginId(user.getUsername());
            reservation.setNumber(member.get().getNumber());
            model.addAttribute("reservation", reservation);
            return "/reservation/checkOut";
        } else {
            // 비회원 예약
            ReservationNonMember reservationNonMember = new ReservationNonMember();
            reservationNonMember.setCheckIn(reservation.getCheckIn());
            reservationNonMember.setCheckOut(reservation.getCheckOut());
            reservationNonMember.setPersonnel(reservation.getPersonnel());
            model.addAttribute("reservationNonMember", reservationNonMember);
            return "/reservation/nonMemberCheckOut";
        }
    }

    // post : 회원 예약 하기
    @PostMapping("/checkout/{roomId}")
    public String memberCheckOut(@PathVariable Long roomId, @Validated @ModelAttribute("reservation") Reservation reservation,
                                 BindingResult bindingResult,
                                 @AuthenticationPrincipal UserDetails user) {

        log.info("회원 예약 날짜 확인={}", reservation.getCheckIn());

        if (bindingResult.hasErrors()) {
            log.info("예약 정보 오류 발생={}", bindingResult);
            return "/reservation/checkOut";
        }

        if (user != null) { // 회원 예약인 경우
            String loginId = user.getUsername();
            Long memberId = memberRepository.findIdByLoginId(loginId);
            reservation.setMember(memberId);
        }

        Room room = accommodationService.findRoomById(roomId);
        Accommodation accommodation = accommodationService.findAccommoById(room.getAccommodation()).get();

        // 만약 해당 기간에 먼저 예약된 방이 있으면 오류를 내보내줘야함.

        reservation.setSeller(accommodation.getSeller());
        reservation.setAmount(room.getBasePrice());
        reservation.setRoom(room.getRoomId());
        reservation.setRDate(new Date());
        reservation.setAccommodation(accommodation.getAccommodationId());

        reservationService.save(reservation);

        log.info("정상 작동, 넘어온 예약정보={}", reservation);
        return "redirect:/reservation/success";
    }

    // post : 비회원 예약 하기
    @PostMapping("/checkout/non-member/{roomId}")
    public String nonMemberCheckOut(@PathVariable Long roomId, @Validated @ModelAttribute("reservationNonMember") ReservationNonMember reservation,
                                    BindingResult bindingResult,
                                    @AuthenticationPrincipal UserDetails user) {

        log.info("비회원 예약 날짜 확인={}", reservation.getCheckIn());

        if (bindingResult.hasErrors()) {
            log.info("예약 정보 오류 발생={}", bindingResult);
            return "/reservation/checkOut";
        }

        Room room = accommodationService.findRoomById(roomId);
        Accommodation accommodation = accommodationService.findAccommoById(room.getAccommodation()).get();

        // 만약 해당 기간에 먼저 예약된 방이 있으면 오류를 내보내줘야함.

        reservation.setSeller(accommodation.getSeller());
        reservation.setAmount(room.getBasePrice());
        reservation.setRoom(room.getRoomId());
        reservation.setRDate(new Date());
        reservation.setAccommodation(accommodation.getAccommodationId());

        String encodedPw = passwordEncoder.encode(reservation.getPassword());
        reservation.setPassword(encodedPw);

        // 여기 비회원 예약 save 하도록 만들어야함.
        reservationService.nonMemberSave(reservation);

        log.info("정상 작동, 넘어온 예약정보={}", reservation);
        return "redirect:/reservation/success";
    }

    @GetMapping("/success")
    public String reservationSuccess() {
        // 예약 성공하면 예약 페이지로 이동

        return "/reservation/reservationSuccess";
    }


    @PostMapping("/payment/validation/{imp_uid}")
    @ResponseBody
    public IamportResponse<Payment> paymentValidation(@PathVariable String imp_uid) {
        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
        log.info("결제 요청 응답. 결제 내역 - 주문 번호: {}", payment.getResponse().getMerchantUid());
        return payment;
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<Room> findRoom(@PathVariable Long roomId) {
        Room room = accommodationService.findRoomById(roomId);
        Long accommodationId = room.getAccommodation();

        Accommodation accommodation = accommodationService.findAccommoById(accommodationId).get();
        Long seller = accommodation.getSeller();

        log.info("현재 예약하려는 방={}", room);
        return ResponseEntity.ok(room);
    }


    // 예약하려는 방이 먼저 예약 되어 있는지 확인.
    @GetMapping("/find/{roomId}")
    @ResponseBody
    public ResponseEntity<Optional<Reservation>> findRommByCond(@PathVariable Long roomId, @RequestParam LocalDate checkIn,
                                                                @RequestParam LocalDate checkOut) {
        Reservation reservationCond = new Reservation(roomId, checkIn, checkOut);
        log.info("들어온 데이터={}", roomId);

        // 데이터베이스 찾아서 나오면 막아야함
        Optional<Reservation> reservationByCond = reservationService.findReservationByCond(reservationCond);
        if (reservationByCond.isPresent()) { // 해당 기간에 예약 내역이 나오게 되면
            log.info("이미 예약 내역이 있음={}", reservationByCond);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reservationByCond);
        }

        return ResponseEntity.ok(reservationByCond);
    }


}
