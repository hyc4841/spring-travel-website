package whang.travel.domain.accommodation.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.domain.accommodation.Accommodation;
import whang.travel.domain.accommodation.AccommodationRepository;
import whang.travel.domain.paging.accommodation.AccommoCriteria;
import whang.travel.web.accommodation.form.AccommoSearchCond;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisAccommoRepository implements AccommodationRepository {

    private final AccommodationMapper accommodationMapper;

    @Override
    public Accommodation save(Accommodation saveAccommo) {
        accommodationMapper.save(saveAccommo);
        log.info("숙소 저장={}", saveAccommo);
        return saveAccommo;
    }

    @Override
    public void update(Long accommoId, Accommodation updateAccommo) {
        log.info("숙소 업데이트");
        accommodationMapper.update(accommoId, updateAccommo);
    }

    @Override
    public Optional<Accommodation> findAccommoById(Long accommoId) {
        Optional<Accommodation> accommo = accommodationMapper.findAccommoById(accommoId);
        log.info("숙소 id로 숙소 찾기={}", accommo);
        return accommo;
    }

    @Override
    public List<Accommodation> findAccommoList(AccommoCriteria criteria) {
        List<Accommodation> accommoList = accommodationMapper.findAccommoList(criteria);
        log.info("조건으로 숙소 검색하기={}", accommoList);
        return accommoList;
    }

    @Override
    public Integer countAccommo(AccommoCriteria criteria) {
        return accommodationMapper.countAccommo(criteria);
    }

    @Override
    public List<Room> findRoomList(Long accommoId, AccommoSearchCond searchCond) {
        List<Room> roomList = accommodationMapper.findRoomList(accommoId, searchCond);
        log.info("예약 가능한 숙소={}", roomList);
        return roomList;
    }

    @Override
    public Room findRoomById(Long roomId) {
        Room findRoom = accommodationMapper.findRoomById(roomId);
        log.info("에약하려는 방={}", findRoom);
        return findRoom;
    }

    @Override
    public void delete(Long accommoId) {
        log.info("숙소 삭제");
        accommodationMapper.delete(accommoId);
    }
}
