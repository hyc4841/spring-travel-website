package whang.travel.domain.order;

import org.springframework.stereotype.Repository;
import whang.travel.web.order.form.SaveOrderForm;
import whang.travel.web.order.form.UpdateOrderForm;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class MemoryOrderRepository implements OrderRepository{

    private static final Map<Long, Order> store = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    @Override // SaveForm으로 데이터 받아서 저장하기
    public Order orderSave(SaveOrderForm saveOrder) {
        Order order = new Order();
        order.setOrderNum(++sequence);
        order.setOrderItem(saveOrder.getOrderItem());
        order.setOrderItemPrice(saveOrder.getOrderItemPrice());
        order.setOrderItemQuantity(saveOrder.getOrderItemQuantity());
        order.setOrderMemberID(saveOrder.getOrderMemberID());
        order.setOrderDate(saveOrder.getOrderDate());
        order.setOrderFinish(false);
        store.put(order.getOrderNum(), order);

        return order;
    }

    @Override // updateForm으로 데이터 업데이트 하기
    public void orderUpdate(Long orderNum, UpdateOrderForm updateOrder) {
        Order order = store.get(orderNum);
        order.setOrderItemPrice(updateOrder.getOrderItemPrice());
        order.setOrderItemQuantity(updateOrder.getOrderItemQuantity());
        order.setOrderMemberID(updateOrder.getOrderMemberID());
        order.setOrderFinish(updateOrder.getOrderFinish());
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
    public void deleteOrder(Long orderNum) {
        store.remove(orderNum);
    }

}
