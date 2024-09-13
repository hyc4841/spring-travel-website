package whang.travel.domain.reservation.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import whang.travel.domain.reservation.Reservation;
import whang.travel.web.reservation.form.ReservationSearchCond;
import whang.travel.web.reservation.form.UpdateReservationForm;

import java.util.List;

@Mapper
public interface ReservationMapper {

    void save(Reservation reservation);

    void update(UpdateReservationForm updateForm);

    Reservation findReservationById(Long reservationId);

    List<Reservation> findReservationList(@Param("memberId") Long memberId, @Param("searchCond") ReservationSearchCond searchCond);

    void delete(Long reservationId);
}
