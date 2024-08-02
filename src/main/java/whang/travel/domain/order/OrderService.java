package whang.travel.domain.order;

import whang.travel.web.order.form.SaveOrderForm;
import whang.travel.web.order.form.UpdateOrderForm;

import java.util.List;

public interface OrderService {
    // 주문하기
    Order createOrder(SaveOrderForm saveOrder);
    // 단건 주문 조회하기(주문 번호 조회)
    Order findOrderByOrderNum(Long orderNum);

    List<Order> findOrderByMemberId(String memberId);
    // 주문 취소하기
    void cancelOrder(Long OrderNum);
    // 주문 수정하기(수량만?)
    void updateOrder(Long orderNum, UpdateOrderForm updateOrder);
}
