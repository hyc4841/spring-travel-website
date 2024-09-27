package whang.travel.domain.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ReservationNonMember {

    private Long reservationId;
    // 비회원이기 때문에 멤버 없어도 됨
    private Long seller;
    private Long accommodation;
    private Long room;
    private Integer personnel;
    private Date rDate; // 예약 날짜임
    private LocalDate checkIn;
    private LocalDate checkOut;
    @NotBlank
    private String customer;
    @NotBlank
    private String number;
    private Integer amount;

    private String visitType;

    @NotBlank
    private String password;

    // amount로 퉁치지 말고
    // 원래 방 가격
    // 할인 가격
    // 총 금액
    // 이렇게 세부적으로 가야함
    // 그리고 할인에 대해선 테이블 따로 만들어서 관리해야함

    public ReservationNonMember() {
    }

    public ReservationNonMember(Long reservationId, Long seller, Long accommodation, Long room, Integer personnel, Date rDate, LocalDate checkIn, LocalDate checkOut, String customer, String number, Integer amount, String visitType) {
        this.reservationId = reservationId;

        this.seller = seller;
        this.accommodation = accommodation;
        this.room = room;
        this.personnel = personnel;
        this.rDate = rDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.customer = customer;
        this.number = number;
        this.amount = amount;
        this.visitType = visitType;
    }

    public ReservationNonMember(Long room, LocalDate checkIn, LocalDate checkOut) {
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}



