package whang.travel.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.parsing.BeanEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import whang.travel.domain.bulletinboard.PostRepository;
import whang.travel.domain.bulletinboard.PostService;
import whang.travel.domain.bulletinboard.PostServiceImpl;
import whang.travel.domain.bulletinboard.mybatis.MybatisPostRepository;
import whang.travel.domain.bulletinboard.mybatis.PostMapper;
import whang.travel.domain.image.ImageRepository;
import whang.travel.domain.image.ImageStore;
import whang.travel.domain.image.mybatis.ImageMapper;
import whang.travel.domain.image.mybatis.MybatisImageRepository;
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
    private final PostMapper postMapper;
    private final ImageMapper imageMapper;

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


    @Bean
    public PostService postService() {
        return new PostServiceImpl(postRepository(), imageRepository(), imageStore());
    }

    @Bean
    public PostRepository postRepository() {
        return new MybatisPostRepository(postMapper);
    }

    @Bean
    public ImageRepository imageRepository() {
        return new MybatisImageRepository(imageMapper);
    }

    @Bean
    public ImageStore imageStore() {
        return new ImageStore();
    }

}
