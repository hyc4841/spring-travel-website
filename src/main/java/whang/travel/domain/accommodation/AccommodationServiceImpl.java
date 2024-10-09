package whang.travel.domain.accommodation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whang.travel.domain.accommodation.mybatis.Room;
import whang.travel.domain.paging.accommodation.AccommoCriteria;
import whang.travel.web.accommodation.form.AccommoSearchCond;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommoRepository;


    @Override
    public Optional<Accommodation> findAccommoById(Long accommoId) {
        return accommoRepository.findAccommoById(accommoId);
    }

    @Override
    public List<Accommodation> findAccommoList(AccommoCriteria criteria) {
        return accommoRepository.findAccommoList(criteria);
    }

    @Override
    public List<Room> findRoomList(Long accommoId, AccommoSearchCond searchCond) {
        return accommoRepository.findRoomList(accommoId, searchCond);
    }

    @Override
    public Integer countAccommo(AccommoCriteria criteria) {
        return accommoRepository.countAccommo(criteria);
    }

    @Override
    public Room findRoomById(Long roomId) {
        return accommoRepository.findRoomById(roomId);
    }

    public void save(Accommodation accommodation) {
        accommoRepository.save(accommodation);
    }
}
