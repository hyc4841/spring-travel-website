package whang.travel.domain.reservation;

import whang.travel.web.profile.form.NonMember;
import whang.travel.web.reservation.form.ReservationSearchCond;
import whang.travel.web.reservation.form.UpdateReservationForm;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

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

    // 예약 내역 보여줄 때
    ReservationShow findReservationListByReservationId(Long reservationId);

    List<ReservationShow> findReservationListByMemberId(Long memberId);

    // 예약 다중 조회
    List<Reservation> findReservationList(Long memberId, ReservationSearchCond searchCond); // 주로 회원의 예약 내역을 모두 조회하는거 + 기간 조회 + 뭐 각종 조건 조회일듯

    Optional<Reservation> findReservationByCond(Reservation reservation);

    //비회원 예약내역 조회
    Optional<ReservationShow> findReservationNonMember(NonMember nonMember);


    // 예약 삭제
    void delete(Long reservationId);

}
