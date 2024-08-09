package whang.travel.domain.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import whang.travel.domain.item.Item;
import whang.travel.domain.item.ItemRepository;
import whang.travel.web.item.form.ItemSaveForm;
import whang.travel.web.item.form.ItemSearchCond;
import whang.travel.web.item.form.ItemUpdateForm;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional // 이 에노테이션이 테스트에서 트랜잭션을 적용하고 롤백해준다.
@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    void save() { // 데이터베이스 저장 잘 자동하는지 테스트
        // given
        ItemSaveForm itemA = new ItemSaveForm("itemA", 10000, 10);

        // when
        Item saveItem = itemRepository.save(itemA);

        // then
        Item finditem = itemRepository.findById(saveItem.getItemId()).get();
        assertThat(finditem).isEqualTo(saveItem);
    }

    @Test
    void UpdateItem() {
        // given
        ItemSaveForm item = new ItemSaveForm("item1", 10000, 10);
        Item saveItem = itemRepository.save(item);
        Long itemId = saveItem.getItemId();

        // when
        ItemUpdateForm updateItem = new ItemUpdateForm("item2", 20000, 20);
        itemRepository.update(itemId, updateItem);

        // then
        Item findItem = itemRepository.findById(itemId).get();
        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }

    @Test
    void findItems() {
        // given
        ItemSaveForm item1 = new ItemSaveForm("itemA-1", 10000, 10);
        ItemSaveForm item2 = new ItemSaveForm("itemA-2", 20000, 20);
        ItemSaveForm item3 = new ItemSaveForm("itemB-1", 30000, 30);

        Item save1 = itemRepository.save(item1);
        Item save2 = itemRepository.save(item2);
        Item save3 = itemRepository.save(item3);

        // when
        // 둘다 없는 경우
        test(null, null, save1, save2, save3);
        test("", null, save1, save2, save3);

        // itemName 부분 검증
        test("itemA", null, save1, save2);
        test("temA", null, save1, save2);
        test("itemB", null, save3);

        // maxPrice 부분 검증
        test(null, 10000, save1);

        // 둘 다 있는 경우
        test("itemA", 10000, save1);
    }

    void test(String itemName, Integer maxPrice, Item... items) {
        List<Item> result = itemRepository.findAll(new ItemSearchCond(itemName, maxPrice));
        assertThat(result).containsExactly(items);
    }


}























