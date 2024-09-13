package whang.travel.web.reservation.errorForm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationApiErrorForm {
    private String code;
    private String message;
}
