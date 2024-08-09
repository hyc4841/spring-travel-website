package whang.travel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import whang.travel.domain.item.ItemRepository;
import whang.travel.web.item.form.ItemSaveForm;

@Slf4j
@RequiredArgsConstructor
public class TestData {

    private final ItemRepository itemRepository;

    /**
     * 테스트용 데이터
     */
    @EventListener(ApplicationReadyEvent.class)
    public void testData() {
        log.info("테스트 데이터");
        itemRepository.save(new ItemSaveForm("itemA", 1000, 100));
        itemRepository.save(new ItemSaveForm("itemB", 2000, 300));
    }

}
