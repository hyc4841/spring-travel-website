package whang.travel.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import whang.travel.domain.item.ItemRepository;
import whang.travel.domain.item.ItemService;
import whang.travel.domain.item.ItemServiceImpl;
import whang.travel.domain.item.mybatis.ItemMapper;
import whang.travel.domain.item.mybatis.MybatisItemRepository;

@Configuration
@RequiredArgsConstructor
public class MybatisConfig {

    private final ItemMapper itemMapper;

    @Bean
    public ItemService itemService() {
        return new ItemServiceImpl(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new MybatisItemRepository(itemMapper);
    }


}
