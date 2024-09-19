package whang.travel.domain.reservation;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Reservation {

    private Long reservationId;
    private Long member;
    private Long seller;
    private Long accommodation;
    private Long room;
    private Integer personnel;
    private Date rDate; // 예약 날짜임
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String customer;
    private String number;
    private Integer amount;

}
