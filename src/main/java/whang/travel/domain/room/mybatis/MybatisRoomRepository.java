package whang.travel.domain.room.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MybatisRoomRepository {

    private final RoomMapper roomMapper;


}
