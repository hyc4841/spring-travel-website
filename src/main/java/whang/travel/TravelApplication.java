package whang.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import whang.travel.domain.item.ItemRepository;
import whang.travel.domain.item.mybatis.MybatisItemRepository;
import whang.travel.domain.member.MemberRepository;

@Import(MybatisItemRepository.class)
@SpringBootApplication
public class TravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

	@Bean
	@Profile("local")
	public TestData testData(ItemRepository itemRepository, MemberRepository memberRepository) {
		return new TestData(itemRepository, memberRepository);
	}
}
