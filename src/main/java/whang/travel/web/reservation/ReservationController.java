package whang.travel.web.reservation;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whang.travel.domain.accommodation.Accommodation;
import whang.travel.domain.accommodation.AccommodationRepository;
import whang.travel.domain.accommodation.AccommodationService;
import whang.travel.domain.accommodation.mybatis.Room;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.domain.reservation.Reservation;
import whang.travel.domain.reservation.mybatis.ReservationService;
import whang.travel.web.reservation.form.ReservationApiResponse;

import java.util.Optional;

@Slf4j
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Controller
public class ReservationController {

    private final MemberRepository memberRepository;
    private final ReservationService reservationService;
    private final AccommodationService accommodationService;

    private IamportClient iamportClient;

    @Value("${imp.api.key}")
    private String apiKey;
    @Value("${imp.api.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }


    @GetMapping("/checkout/{roomId}")
    public String reservationView(@PathVariable Long roomId, @ModelAttribute("reservation") Reservation reservation,
                                  Model model, @AuthenticationPrincipal UserDetails user) {

        if (user != null) {
            Optional<Member> member = memberRepository.findByLoginId(user.getUsername());
            reservation.setNumber(member.get().getNumber());
        }

        Room room = accommodationService.findRoomById(roomId);
        model.addAttribute("room", room);
        // 예약할 때 무슨 데이터가 넘어가야 할까?
        // 예약자, 숙소 이름

        return "/reservation/addReservation";
    }

    @PostMapping("/checkout/{roomId}")
    public ResponseEntity<String> addReservation(@RequestBody Reservation reservation, @AuthenticationPrincipal UserDetails user) {
        String loginId = user.getUsername();
        Long memberId = memberRepository.findIdByLoginId(loginId);
        // 판매자 가져오기 => 숙소 id로 가져올 수 있다.
        // 숙소 가져오기
        // 예약 날짜
        //
        try {
            reservation.setMember(memberId);
            Reservation save = reservationService.save(reservation);
            log.info("결재 성공={}", save);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            log.info("오류 발생");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/payment/validation/{imp_uid}")
    @ResponseBody
    public IamportResponse<Payment> paymentValidation(@PathVariable String imp_uid) {
        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
        log.info("결제 요청 응답. 결제 내역 - 주문 번호: {}", payment.getResponse().getMerchantUid());
        return payment;
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ResponseEntity<Room> findRoom(@PathVariable Long roomId) {
        Room room = accommodationService.findRoomById(roomId);
        Long accommodationId = room.getAccommodation();

        Accommodation accommodation = accommodationService.findAccommoById(accommodationId).get();
        Long seller = accommodation.getSeller();

        log.info("예약하려는 방 찾기={}", room);
        return ResponseEntity.ok(room);
    }

}
