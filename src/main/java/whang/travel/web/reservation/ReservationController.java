package whang.travel.web.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    @GetMapping("/add/{roomId}")
    public String reservationView() {

        return "/reservation/add";
    }

    @PostMapping("/add/{roomId}")
    public String addReservation() {



        return "redirect:/reservation/successReservation";
    }
}
