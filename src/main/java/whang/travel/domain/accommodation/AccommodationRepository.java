package whang.travel.domain.accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository {
    // 숙소 등록은 판매자(또는 관리자) 페이지에서 이루어질 것임.
    // 저장
    Accommodation save(Accommodation saveAccommo); // 추후에 저장 폼 만들어서 바꿔주기

    // 수정
    void update(Long AccommoId, Accommodation updateAccommo); // 추후에 업데이트 폼 만들어서 바꿔주기

    // 단건 조회
    Optional<Accommodation> findAccommo(Long AccommoId);

    // 다중조회
    List<Accommodation> findAccommoList(); //

    /*
    숙소 검색은 주로 지역 검색으로 이루어질 것임.
    지역 + 날짜 + 가능 인원
     */

    // 삭제
    void delete(Long AccommoId);


}
