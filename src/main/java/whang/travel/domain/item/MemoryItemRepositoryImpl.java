package whang.travel.domain.item;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.web.item.form.ItemSaveForm;
import whang.travel.web.item.form.ItemSearchCond;
import whang.travel.web.item.form.ItemUpdateForm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
//@Repository
public class MemoryItemRepositoryImpl implements ItemRepository{
    // 메모리 버전의 아이템 리포지토리
    private static final Map<Long, Item> store = new ConcurrentHashMap<>();
    private static Long itemNum = 0L;

    @Override
    public Item save(ItemSaveForm saveItem) {
        Item item = new Item();
        item.setItemId(++itemNum);
        item.setItemName(saveItem.getItemName());
        item.setPrice(saveItem.getPrice());
        item.setQuantity(saveItem.getQuantity());
        store.put(item.getItemId(), item);

        log.info("상품 등록 성공={}", item);

        return item;
    }
    @Override
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }


    @Override
    public void update(Long id, ItemUpdateForm updateForm) {
        Item item = findById(id).orElseThrow(); // 먼저 id로 아이템을 찾는다
        item.setItemName(updateForm.getItemName());
        item.setQuantity(updateForm.getQuantity());
        item.setPrice(updateForm.getPrice());
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
