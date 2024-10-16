package whang.travel.domain.accommodation;

import whang.travel.domain.accommodation.mybatis.Room;
import whang.travel.domain.paging.accommodation.AccommoCriteria;
import whang.travel.web.accommodation.form.AccommoSearchCond;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository {
    // 숙소 등록 및 수정은 판매자(또는 관리자) 페이지에서 이루어질 것임.
    // 저장
    Accommodation save(Accommodation saveAccommo); // 추후에 저장 폼 만들어서 바꿔주기
    // 수정
    void update(Long accommoId, Accommodation updateAccommo); // 추후에 업데이트 폼 만들어서 바꿔주기

    // 단건 조회 : 숙소 상세 페이지 들어갈 때
    Optional<Accommodation> findAccommoById(Long accommoId);

    // 다중조회 : (지역, 기간, 인원) 종합해서 숙소 리스트 검색
    List<Accommodation> findAccommoList(AccommoCriteria criteria);

    // 페이징에 사용할 조건 검색 총 개수
    Integer countAccommo(AccommoCriteria criteria);

    List<Room> findRoomList(Long accommoId, AccommoSearchCond searchCond);

    Room findRoomById(Long roomId);

    // 삭제
    void delete(Long accommoId);


}
