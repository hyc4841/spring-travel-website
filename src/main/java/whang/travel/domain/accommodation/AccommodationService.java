package whang.travel.domain.accommodation;

import whang.travel.domain.accommodation.mybatis.Room;
import whang.travel.domain.paging.accommodation.AccommoCriteria;
import whang.travel.web.accommodation.form.AccommoSearchCond;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    public Optional<Accommodation> findAccommoById(Long accommoId);

    List<Accommodation> findAccommoList(AccommoCriteria criteria);

    List<Room> findRoomList(Long accommoId, AccommoSearchCond searchCond);

    Integer countAccommo(AccommoCriteria criteria);

    Room findRoomById(Long roomId);

    void save(Accommodation accommodation);

}
