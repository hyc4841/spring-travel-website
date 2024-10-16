package whang.travel.domain.reservation;

import whang.travel.web.profile.form.NonMember;
import whang.travel.web.reservation.form.ReservationSearchCond;
import whang.travel.web.reservation.form.UpdateReservationForm;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    // 예약 저장
    Reservation save(Reservation reservation);

    // 비회원 예약 저장
    ReservationNonMember nonMemberSave(ReservationNonMember reservation);

    // 예약 수정
    void update(UpdateReservationForm updateForm);
    // 예약 단건 조회

    Optional<Reservation> findReservationById(Long reservationId);

    // 비회원 예약 내역 검색
    List<Reservation> findNonMemberReservation(NonMember nonMember);


    ReservationShow findReservationListByReservationId(Long reservationId);

    // 예약내역 조회할 때
    List<ReservationShow> findReservationListByMemberId(Long memberId);

    // 예약 다중 조회
    List<Reservation> findReservationList(Long memberId, ReservationSearchCond searchCond);

    // 해당 기간에 먼저 예약된 내역이 있는지 확인. 여기서 조건은 checkInm, checkOut임
    Optional<Reservation> findReservationByCond(Reservation reservation);

    // 비회원 예약내역 조회
    Optional<ReservationShow> findReservationNonMember(NonMember nonMember);

    // 예약 삭제
    void delete(Long reservationId);
}
