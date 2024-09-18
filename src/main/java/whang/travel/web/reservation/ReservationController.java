package whang.travel.web.reservation;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whang.travel.domain.accommodation.AccommodationRepository;
import whang.travel.domain.accommodation.AccommodationService;
import whang.travel.domain.accommodation.mybatis.Room;
import whang.travel.domain.reservation.Reservation;
import whang.travel.domain.reservation.mybatis.ReservationService;
import whang.travel.web.reservation.form.ReservationApiResponse;

@Slf4j
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final AccommodationService accommodationService;

    @GetMapping("/checkout/{roomId}")
    public String reservationView(@PathVariable Long roomId, @ModelAttribute("reservation") Reservation reservation,
                                  Model model, @AuthenticationPrincipal UserDetails user) {

        Room room = accommodationService.findRoomById(roomId);
        model.addAttribute("room", room);
        // 예약할 때 무슨 데이터가 넘어가야 할까?

        // 예약자, 숙소 이름

        return "/reservation/addReservation";
    }

    @PostMapping("")
    public String addReservation(@RequestBody Reservation reservation) {

        //

        return "redirect:/reservation/successReservation";
    }
}
