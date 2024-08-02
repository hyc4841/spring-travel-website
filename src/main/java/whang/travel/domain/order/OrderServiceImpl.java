package whang.travel.domain.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whang.travel.web.order.form.SaveOrderForm;
import whang.travel.web.order.form.UpdateOrderForm;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;


    @Override
    public Order createOrder(SaveOrderForm saveOrder) {
        return orderRepository.orderSave(saveOrder);
    }

    @Override
    public Order findOrderByOrderNum(Long orderNum) {
        return orderRepository.findOrderByOrderNum(orderNum);
    }

    @Override
    public List<Order> findOrderByMemberId(String memberId) {
        return orderRepository.findOrderByMember(memberId);
    }

    @Override
    public void cancelOrder(Long OrderNum) {
        orderRepository.deleteOrder(OrderNum);
    }

    @Override
    public void updateOrder(Long orderNum, UpdateOrderForm updateOrder) {
        orderRepository.orderUpdate(orderNum, updateOrder);
    }
}
