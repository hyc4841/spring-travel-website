package whang.travel.domain.reservation.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import whang.travel.domain.reservation.Reservation;
import whang.travel.domain.reservation.ReservationNonMember;
import whang.travel.domain.reservation.ReservationShow;
import whang.travel.web.profile.form.NonMember;
import whang.travel.web.reservation.form.ReservationSearchCond;
import whang.travel.web.reservation.form.UpdateReservationForm;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReservationMapper {

    // 회원 예약 저장
    void save(Reservation reservation);

    // 비회원 예약 저장
    void nonMemberSave(ReservationNonMember reservation);

    // 예약 내역 수정
    void update(UpdateReservationForm updateForm);

    Reservation findReservationById(Long reservationId);

    ReservationShow findReservationListByReservationId(Long reservationId);

    List<ReservationShow> findReservationListByMemberId(Long memberId);

    List<Reservation> findReservationList(@Param("memberId") Long memberId, @Param("searchCond") ReservationSearchCond searchCond);

    Optional<Reservation> findReservationByCond(Reservation reservation);

    Optional<ReservationShow> findReservationNonMember(NonMember nonMember);

    void delete(Long reservationId);


}
