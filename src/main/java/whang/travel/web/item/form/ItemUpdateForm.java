package whang.travel.web.item.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemUpdateForm {

    @NotBlank
    private String itemName;
    @NotNull
    @Range(min = 100, max = 1000000)
    private Integer price;
    // 수량 자유로 설정
    @NotNull
    private Integer quantity;


    public ItemUpdateForm(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
