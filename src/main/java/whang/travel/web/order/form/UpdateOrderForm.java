package whang.travel.web.order.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

@Data
public class UpdateOrderForm {


    @NotNull
    private Long orderNum; // 주문 번호
    @NotBlank
    private String orderMemberID; // 주문한 맴버
    @NotNull
    private Long orderItemId; // 주문 상품 id
    @NotBlank
    private String orderItem; // 주문한 상품
    @NotNull
    private Integer orderItemPrice; // 주문한 상품의 가격
    @NotNull
    @Range(min = 1, max = 9999)
    private Integer orderItemQuantity; // 주문한 상품 수량
    @NotBlank
    private String address; // 주소
    @NotBlank
    private String orderDate; // 주문 날짜
    @NotNull
    private Integer totalPrice; // 총 주문 금액

}
