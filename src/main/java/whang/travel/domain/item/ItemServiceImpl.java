package whang.travel.domain.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whang.travel.web.item.form.ItemSaveForm;
import whang.travel.web.item.form.ItemSearchCond;
import whang.travel.web.item.form.ItemUpdateForm;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    // 리포지토리에 접근해야함
    private final ItemRepository itemRepository;

    @Override
    public Item save(ItemSaveForm item) {
        return itemRepository.save(item);
    }

    @Override
    public void update(Long id, ItemUpdateForm updateForm) {
        itemRepository.update(id, updateForm);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        return itemRepository.findAll(cond);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.delete(id);
    }
}
