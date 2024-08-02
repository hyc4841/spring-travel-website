package whang.travel.web.order;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import whang.travel.domain.member.Member;
import whang.travel.domain.order.OrderService;
import whang.travel.web.SessionConst;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    // 상품 화면 -> 주문 화면 -> 주문
    private final OrderService orderService;

    // 항상 로그인한 유저를 확인해야한다.

    // 주문 확인 화면
    @GetMapping("/addOrder")
    public String addOrderForm(HttpServletRequest request, Model model) {
        // 현재 유저가 누구인지 확인
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember == null) {
            log.info("현재 로그인 멤버 없음 로그인 하시오");
            return "/login";
        }

        model.addAttribute("loginMember", loginMember);
        return "/order/addOrderForm";
    }

    // 주문
    @PostMapping("/addOrder")
    public String createOrder() {

    }

    // 주문 목록 화면(멤버 id로 조회)
    @GetMapping("/orderList/{memberId}")
    public String orderList() {

    }

    // 주문 수정 화면
    @GetMapping("/editOrder/{orderNum}")
    public String editOrderForm() {

    }

    // 주문 수정
    @PostMapping("/editOrder/{orderNum}")
    public String editOrder() {

    }

    //
    @DeleteMapping("/deleteOrder")
    public String deleteOrder() {

    }
}
