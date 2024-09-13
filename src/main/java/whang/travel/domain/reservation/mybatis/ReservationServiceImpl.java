package whang.travel.domain.reservation.mybatis;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whang.travel.domain.reservation.Reservation;
import whang.travel.domain.reservation.ReservationRepository;
import whang.travel.web.reservation.form.ReservationSearchCond;
import whang.travel.web.reservation.form.UpdateReservationForm;

import java.util.List;

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
    public void update(UpdateReservationForm updateForm) {
        reservationRepository.update(updateForm);
    }

    @Override
    public Reservation findReservationById(Long reservationId) {
        return reservationRepository.findReservationById(reservationId);
    }

    @Override
    public List<Reservation> findReservationList(Long memberId, ReservationSearchCond searchCond) {
        return reservationRepository.findReservationList(memberId,searchCond);
    }

    @Override
    public void delete(Long reservationId) {
        reservationRepository.delete(reservationId);
    }
}
