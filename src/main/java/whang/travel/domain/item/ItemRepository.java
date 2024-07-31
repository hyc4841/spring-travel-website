package whang.travel.domain.item;

import whang.travel.web.item.form.ItemSaveForm;
import whang.travel.web.item.form.ItemSearchCond;
import whang.travel.web.item.form.ItemUpdateForm;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    // 아이템 저장
    Item save(ItemSaveForm item); // 아이템 객체를 받아서 데이터베이스에 저장

    // 아이템 수정
    void update(Long id, ItemUpdateForm updateForm); // 조회할 아이템의 등록 번호와 수정할 데이터를 받아서 갱신한다.

    // 아이템 단건 조회
    Optional<Item> findById(Long id); // 아이템 등록 번호로 단건 조회
    // 아이템 전부 조회
    List<Item> findAll(ItemSearchCond cond); // 아이템 검색 조건을 넣어서 아이템을 조회한다.

    // 아이템 삭제
    void delete(Long id); // 아이템 등록 번호로 해당 아이템 삭제
}
