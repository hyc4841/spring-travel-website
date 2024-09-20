package whang.travel.domain.reservation.mybatis;

import whang.travel.domain.reservation.Reservation;
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
    // 예약 다중 조회
    List<Reservation> findReservationList(Long memberId, ReservationSearchCond searchCond);

    Optional<Reservation> findReservationByCond(Reservation reservation);
    // 예약 삭제
    void delete(Long reservationId);
}
