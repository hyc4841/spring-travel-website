package whang.travel.web.order.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

@Data
public class UpdateOrderForm {

    @NotNull
    @Range(min = 1, max = 9999)
    private Integer orderItemQuantity; // 주문한 상품 수량
    @NotNull
    private Integer orderItemPrice; // 주문한 상품의 가격
    // 수량이 변경되면 주문 금액도 바뀐다
}
