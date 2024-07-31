package whang.travel.domain.item;

import whang.travel.web.item.form.ItemSaveForm;
import whang.travel.web.item.form.ItemSearchCond;
import whang.travel.web.item.form.ItemUpdateForm;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    // 아이템 저장
    Item save(ItemSaveForm item);

    // 아이템 수정
    void update(Long id, ItemUpdateForm updateForm);

    // 아이템 단건 조회
    Optional<Item> findById(Long id);

    // 아이템 전부 조회
    List<Item> findAll(ItemSearchCond cond);
}
