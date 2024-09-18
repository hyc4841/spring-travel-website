package whang.travel.domain.accommodation;

import whang.travel.domain.accommodation.mybatis.Room;
import whang.travel.web.accommodation.form.AccommoSearchCond;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    public Optional<Accommodation> findAccommoById(Long accommoId);

    List<Accommodation> findAccommoList(AccommoSearchCond accommoSearchCond);

    List<Room> findRoomList(Long accommoId, AccommoSearchCond searchCond);

    Room findRoomById(Long roomId);

    void save(Accommodation accommodation);

}
