package whang.travel.domain.item.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.domain.item.Item;
import whang.travel.domain.item.ItemRepository;
import whang.travel.web.item.form.ItemSaveForm;
import whang.travel.web.item.form.ItemSearchCond;
import whang.travel.web.item.form.ItemUpdateForm;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisItemRepository implements ItemRepository {

    private final ItemMapper itemMapper;

    @Override
    public Item save(ItemSaveForm item) {
        Item saveItem = new Item();
        saveItem.setItemName(item.getItemName());
        saveItem.setPrice(item.getPrice());
        saveItem.setQuantity(item.getQuantity());
        itemMapper.save(saveItem);

        log.info("상품 저장 성공={}", saveItem);
        return saveItem;
    }

    @Override
    public void update(Long id, ItemUpdateForm updateForm) {
        log.info("상품 업데이트={}", updateForm);
        itemMapper.update(id, updateForm);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemMapper.findById(id);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        return itemMapper.findAll(cond);
    }

    @Override
    public void delete(Long id) {

    }
}
