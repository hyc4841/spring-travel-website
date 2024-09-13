package whang.travel.domain.reservation;

import lombok.Data;

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
    private Date checkIn;
    private Date checkOut;

}
