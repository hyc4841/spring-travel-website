package whang.travel.domain.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.web.order.form.SaveOrderForm;
import whang.travel.web.order.form.UpdateOrderForm;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Repository
public class MemoryOrderRepository implements OrderRepository{

    private static final Map<Long, Order> store = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    @Override // SaveForm으로 데이터 받아서 저장하기
    public Order orderSave(SaveOrderForm saveOrder) {
        Order order = new Order();
        order.setOrderNum(++sequence); // 주문 번호 자동 증가
        order.setOrderItem(saveOrder.getOrderItem()); // 주문 상품 이름
        order.setOrderItemId(saveOrder.getOrderItemId()); // 주문 상품 ID
        order.setOrderItemPrice(saveOrder.getOrderItemPrice()); // 가격
        order.setOrderItemQuantity(saveOrder.getOrderItemQuantity()); // 수량
        order.setOrderMemberID(saveOrder.getOrderMemberID()); // 주문자 ID
        order.setOrderDate(new Date()); // 주문 날짜
        order.setAddress(saveOrder.getAddress()); // 주소
        order.setTotalPrice(saveOrder.getOrderItemPrice() * saveOrder.getOrderItemQuantity()); // 총 주문 금액
        store.put(order.getOrderNum(), order);

        log.info("주문 저장 성공={}", order);

        return order;
    }

    @Override // updateForm으로 데이터 업데이트 하기
    public void orderUpdate(Long orderNum, UpdateOrderForm updateOrder) {
        // 주문 수정은 상품의 수량과 수량 변화에 따른 총 금액 변화를 업데이트 한다
        Order order = store.get(orderNum);
        order.setOrderItemQuantity(updateOrder.getOrderItemQuantity());
        order.setTotalPrice(updateOrder.getOrderItemPrice() * updateOrder.getOrderItemQuantity());
    }

    @Override //
    public Order findOrderByOrderNum(Long orderNum) {
        return store.get(orderNum);
    }

    @Override
    public List<Order> findOrderByMember(String memberId) {
        List<Order> orders = new ArrayList<>();

        // 이 방법은 추후에 이용자가 많아지면 최악의 방법이다
        for (Order i : store.values()) {
            if (Objects.equals(i.getOrderMemberID(), memberId)) {
                orders.add(i);
            }
        }
        return orders;
    }

    @Override
    public void deleteOrder(Long orderNum) { // 주문 번호로 주문을 삭제한다.
        store.remove(orderNum);
    }

}
