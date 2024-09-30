package whang.travel.domain.reservation.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.domain.reservation.Reservation;
import whang.travel.domain.reservation.ReservationNonMember;
import whang.travel.domain.reservation.ReservationRepository;
import whang.travel.domain.reservation.ReservationShow;
import whang.travel.web.profile.form.NonMember;
import whang.travel.web.reservation.form.ReservationSearchCond;
import whang.travel.web.reservation.form.UpdateReservationForm;

import java.util.List;
import java.util.Optional;

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
    public ReservationNonMember nonMemberSave(ReservationNonMember reservation) {
        reservationMapper.nonMemberSave(reservation);
        log.info("비회원 예약 저장={}", reservation);
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
    public ReservationShow findReservationListByReservationId(Long reservationId) {
        return reservationMapper.findReservationListByReservationId(reservationId);
    }

    @Override
    public List<ReservationShow> findReservationListByMemberId(Long memberId) {
        List<ReservationShow> reservationList = reservationMapper.findReservationListByMemberId(memberId);
        log.info("멤버의 예약 내역 리스트={}", reservationList);
        return reservationList;
    }

    @Override
    public List<Reservation> findReservationList(Long memberId, ReservationSearchCond searchCond) {
        List<Reservation> reservationList = reservationMapper.findReservationList(memberId, searchCond);
        log.info("멤버 id와 검색 조건으로 예약 리스트 조회={}", reservationList);
        return reservationList;
    }

    @Override
    public Optional<Reservation> findReservationByCond(Reservation reservation) {
        Optional<Reservation> reservationByCond = reservationMapper.findReservationByCond(reservation);
        log.info("해당 기간에 기존에 예약된 내역이 있는지={}", reservationByCond);
        return reservationByCond;
    }

    @Override
    public Optional<ReservationShow> findReservationNonMember(NonMember nonMember) {
        Optional<ReservationShow> nonMemberReservation = reservationMapper.findReservationNonMember(nonMember);
        log.info("비회원 예약내역 조회={}", nonMemberReservation);
        return nonMemberReservation;
    }


    @Override
    public void delete(Long reservationId) {
        reservationMapper.delete(reservationId);
        log.info("예약 삭제={}", reservationId);
    }
}
