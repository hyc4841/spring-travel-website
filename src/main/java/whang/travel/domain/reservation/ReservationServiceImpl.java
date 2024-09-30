package whang.travel.domain.reservation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whang.travel.web.profile.form.NonMember;
import whang.travel.web.reservation.form.ReservationSearchCond;
import whang.travel.web.reservation.form.UpdateReservationForm;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public ReservationNonMember nonMemberSave(ReservationNonMember reservation) {
        return reservationRepository.nonMemberSave(reservation);
    }

    @Override
    public void update(UpdateReservationForm updateForm) {
        reservationRepository.update(updateForm);
    }

    @Override
    public Reservation findReservationById(Long reservationId) {
        return reservationRepository.findReservationById(reservationId);
    }

    @Override
    public List<Reservation> findNonMemberReservation(NonMember nonMember) {
        return reservationRepository.findNonMemberReservation(nonMember);
    }

    @Override
    public ReservationShow findReservationListByReservationId(Long reservationId) {
        return reservationRepository.findReservationListByReservationId(reservationId);
    }

    @Override
    public List<ReservationShow> findReservationListByMemberId(Long memberId) {
        return reservationRepository.findReservationListByMemberId(memberId);
    }

    @Override
    public List<Reservation> findReservationList(Long memberId, ReservationSearchCond searchCond) {
        return reservationRepository.findReservationList(memberId,searchCond);
    }

    @Override
    public Optional<Reservation> findReservationByCond(Reservation reservation) {
        return reservationRepository.findReservationByCond(reservation);
    }

    @Override
    public Optional<ReservationShow> findReservationNonMember(NonMember nonMember) {
        return reservationRepository.findReservationNonMember(nonMember);
    }

    @Override
    public void delete(Long reservationId) {
        reservationRepository.delete(reservationId);
    }
}
