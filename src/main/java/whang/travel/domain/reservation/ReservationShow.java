package whang.travel.domain.reservation;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
public class ReservationShow {

    // reservation
    private Long reservationId;
    private LocalDate rDate; // 예약 날짜임
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String visitType;


    // room
    private String roomName; // 방 이름
    private Integer personnel;
    private Integer maxPersonnel;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;

    // accommodation
    private String accommoName;

}
