package whang.travel.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.parsing.BeanEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import whang.travel.domain.item.ItemRepository;
import whang.travel.domain.item.ItemService;
import whang.travel.domain.item.ItemServiceImpl;
import whang.travel.domain.item.mybatis.ItemMapper;
import whang.travel.domain.item.mybatis.MybatisItemRepository;
import whang.travel.domain.member.MemberRepository;
import whang.travel.domain.member.MemberService;
import whang.travel.domain.member.MemberServiceImpl;
import whang.travel.domain.member.mybatis.MemberMapper;
import whang.travel.domain.member.mybatis.MybatisMemberRepository;

@Configuration
@RequiredArgsConstructor
public class MybatisConfig {

    private final ItemMapper itemMapper;
    private final MemberMapper memberMapper;

    // 아이템 부분
    @Bean
    public ItemService itemService() {
        return new ItemServiceImpl(itemRepository());
    }
    @Bean
    public ItemRepository itemRepository() {
        return new MybatisItemRepository(itemMapper);
    }

    // 멤버 부분
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MybatisMemberRepository(memberMapper);
    }


}
