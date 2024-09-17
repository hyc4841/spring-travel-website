package whang.travel.web.reservation;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whang.travel.domain.accommodation.AccommodationRepository;
import whang.travel.domain.accommodation.AccommodationService;
import whang.travel.domain.reservation.Reservation;
import whang.travel.domain.reservation.mybatis.ReservationService;
import whang.travel.web.reservation.form.ReservationApiResponse;

@Slf4j
@RequestMapping("/reservation")
@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationService reservationService;
    private final AccommodationService accommodationService;

    // Api로 하려면 화면 이동은 프론트에서 하고 서버는 데이터만 준다. csr
    @PostMapping("/check/{roomId}")
    public HttpEntity<Reservation> reservationView(@RequestBody Reservation reservation,
                                      Model model, HttpServletResponse response) {

        log.info("넘어온 예약 정보={}", reservation);
        model.addAttribute("reservation", reservation);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

        return ;
    }

    @PostMapping("")
    public String addReservation(@RequestBody Reservation reservation) {

        //

        return "redirect:/reservation/successReservation";
    }
}
