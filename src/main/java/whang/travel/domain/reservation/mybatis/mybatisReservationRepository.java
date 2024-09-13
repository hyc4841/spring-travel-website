package whang.travel.domain.reservation.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.domain.reservation.Reservation;
import whang.travel.domain.reservation.ReservationRepository;
import whang.travel.web.reservation.form.ReservationSearchCond;
import whang.travel.web.reservation.form.UpdateReservationForm;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class mybatisReservationRepository implements ReservationRepository {

    private final ReservationMapper reservationMapper;

    @Override
    public Reservation save(Reservation reservation) {
        reservationMapper.save(reservation);
        log.info("예약 저장={}", reservation);
        return reservation;
    }

    @Override
    public void update(UpdateReservationForm updateForm) {
        reservationMapper.update(updateForm);
        log.info("예약 업데이트={}", updateForm);
    }

    @Override
    public Reservation findReservationById(Long reservationId) {
        Reservation reservation = reservationMapper.findReservationById(reservationId);
        log.info("예약 id로 단건 조회={}", reservation);
        return reservation;
    }

    @Override
    public List<Reservation> findReservationList(Long memberId, ReservationSearchCond searchCond) {
        List<Reservation> reservationList = reservationMapper.findReservationList(memberId, searchCond);
        log.info("멤버 id와 검색 조건으로 예약 리스트 조회={}", reservationList);
        return reservationList;
    }

    @Override
    public void delete(Long reservationId) {
        reservationMapper.delete(reservationId);
        log.info("예약 삭제={}", reservationId);
    }
}
