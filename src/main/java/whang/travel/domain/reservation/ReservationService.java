package whang.travel.domain.reservation;

import whang.travel.web.reservation.form.ReservationSearchCond;
import whang.travel.web.reservation.form.UpdateReservationForm;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    // 예약 저장
    Reservation save(Reservation reservation);
    // 예약 수정
    void update(UpdateReservationForm updateForm);
    // 예약 단건 조회
    Reservation findReservationById(Long reservationId);

    ReservationShow findReservationListByReservationId(Long reservationId);

    List<ReservationShow> findReservationListByMemberId(Long memberId);

    // 예약 다중 조회
    List<Reservation> findReservationList(Long memberId, ReservationSearchCond searchCond);

    // 해당 기간에 먼저 예약된 내역이 있는지 확인. 여기서 조건은 checkInm, checkOut임
    Optional<Reservation> findReservationByCond(Reservation reservation);
    // 예약 삭제
    void delete(Long reservationId);
}
