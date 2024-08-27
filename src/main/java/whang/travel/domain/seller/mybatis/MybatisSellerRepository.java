package whang.travel.domain.seller.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisSellerRepository {

    private final SellerMapper sellerMapper;

}
