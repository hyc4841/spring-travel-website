package whang.travel.domain.reservation.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import whang.travel.domain.reservation.Reservation;
import whang.travel.domain.reservation.ReservationShow;
import whang.travel.web.reservation.form.ReservationSearchCond;
import whang.travel.web.reservation.form.UpdateReservationForm;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReservationMapper {

    void save(Reservation reservation);

    void update(UpdateReservationForm updateForm);

    Reservation findReservationById(Long reservationId);

    ReservationShow findReservationListByReservationId(Long reservationId);

    List<ReservationShow> findReservationListByMemberId(Long memberId);

    List<Reservation> findReservationList(@Param("memberId") Long memberId, @Param("searchCond") ReservationSearchCond searchCond);

    Optional<Reservation> findReservationByCond(Reservation reservation);

    void delete(Long reservationId);


}
