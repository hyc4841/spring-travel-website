package whang.travel.domain.order;

import whang.travel.web.order.form.SaveOrderForm;
import whang.travel.web.order.form.UpdateOrderForm;

import java.util.List;

public interface OrderRepository {

    // 멤버의 주문 저장
    Order orderSave(SaveOrderForm saveOrder); // 주문한 멤버, 주문 상품을 받아서 주문 생성

    // 주문 수정
    void orderUpdate(Long orderNum, UpdateOrderForm updateOrder);

    // 주문 번호로 주문 단건 조회
    Order findOrderByOrderNum(Long orderNum);
    // 멤버의 주문 모두 조회
    List<Order> findOrderByMember(String memberId); // 멤버 id로 해당 멤버의 주문을 조회함.
    // 주문 취소
    void deleteOrder(Long orderNum);

    // 모든 주문 조회?
}
