package whang.travel.domain.order.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import whang.travel.domain.order.Order;
import whang.travel.web.order.form.SaveOrderForm;
import whang.travel.web.order.form.UpdateOrderForm;

import java.util.List;

@Mapper
public interface OrderMapper {

    void save(SaveOrderForm saveOrderForm);

    void update(@Param("orderId") Long orderNum, @Param("updateOrder") UpdateOrderForm updateOrderForm);

    // 단건 조회
    Order findOrderByOrderNum(Long orderNum);

    // 멤버 로그인 id로 다수 조회
    List<Order> findOrderByMemberId(@Param("memberId") Long memberId);

    Long findIdByMemberLoginId(String memberLoginId);

    // 주문 취소
    void delete(Long orderNum);


}
