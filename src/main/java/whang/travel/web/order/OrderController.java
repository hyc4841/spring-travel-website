package whang.travel.web.order;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import whang.travel.domain.item.Item;
import whang.travel.domain.item.ItemService;
import whang.travel.domain.member.Member;
import whang.travel.domain.order.Order;
import whang.travel.domain.order.OrderService;
import whang.travel.web.SessionConst;
import whang.travel.web.order.form.SaveOrderForm;
import whang.travel.web.order.form.UpdateOrderForm;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    // 상품 화면 -> 주문 화면 -> 주문
    private final OrderService orderService;
    private final ItemService itemService;

    // 항상 로그인한 유저를 확인해야한다.

    // 주문 확인 화면
    @GetMapping("/addOrder/{orderItemId}")
    public String addOrderForm(@PathVariable Long orderItemId, HttpServletRequest request, Model model) {
        // 현재 유저가 누구인지 확인
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember == null) {
            log.info("현재 로그인 멤버 없음 로그인 하시오");
            return "/login";
        }

        Item orderItem = itemService.findById(orderItemId).get();

        model.addAttribute("orderItem", orderItem); // 현재 주문 상품
        model.addAttribute("loginMember", loginMember); // 현재 멤버

        return "/order/addOrderForm";
    }

    // 주문
    @PostMapping("/addOrder")
    public String createOrder(@ModelAttribute SaveOrderForm saveOrderForm, RedirectAttributes redirectAttributes) {
        Order order = orderService.createOrder(saveOrderForm);

        redirectAttributes.addAttribute("orderNum", order.getOrderNum());

        return "redirect:/order/success/{orderNum}";
    }

    @GetMapping("/success/{orderNum}")
    public String orderSuccessForm(@PathVariable Long orderNum, Model model) {
        Order order = orderService.findOrderByOrderNum(orderNum);

        model.addAttribute("saveOrder", order);
        return "/order/orderSubmitSuccess";
    }

    // 주문 목록 화면(멤버 id로 조회)
    @GetMapping("/orderList/{memberId}")
    public String orderList(@PathVariable String memberId, Model model) {
        List<Order> orderList = orderService.findOrderByMemberId(memberId);

        model.addAttribute("orderList", orderList);

        return "/order/orderList";
    }

    @GetMapping("/orderDetail/{orderNum}")
    public String orderDetail(@PathVariable Long orderNum, Model model) {
        Order orderDetail = orderService.findOrderByOrderNum(orderNum);

        model.addAttribute("order", orderDetail);

        return "/order/orderDetail";
    }

    // 주문 수정 화면
    @GetMapping("/editOrder/{orderNum}")
    public String editOrderForm(@PathVariable Long orderNum, Model model) {

        Order editOrder = orderService.findOrderByOrderNum(orderNum);

        model.addAttribute("editOrder", editOrder);

        return "/order/editOrder";
    }

    // 주문 수정
    @PostMapping("/editOrder/{orderNum}")
    public String editOrder(@ModelAttribute UpdateOrderForm updateOrder, @PathVariable Long orderNum, RedirectAttributes redirectAttributes) {
        orderService.updateOrder(orderNum, updateOrder);

        redirectAttributes.addAttribute("orderNum", orderNum);

        return "redirect:/order/orderDetail/{orderNum}";
    }

    //
    @DeleteMapping("/deleteOrder/{orderNum}")
    public String cancelOrder(@PathVariable Long orderNum, RedirectAttributes redirectAttributes) {
        Order findOrder = orderService.findOrderByOrderNum(orderNum);
        orderService.cancelOrder(orderNum);

        String memberId = findOrder.getOrderMemberID();
        redirectAttributes.addAttribute("memberId", memberId);

        return "redirect:/order/orderList/{memberId}";
    }
}
