package whang.travel.web.item.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemSaveForm {
    // 아이템 등록 번호는 데이터베이스에서 알아서 1씩 올려준다.

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 100, max = 1000000) // 최소 100원에서 100만원까지
    private Integer price;

    @NotNull
    @Range(min = 1, max = 9999) // 최소 1개부터 9999개 까지
    private Integer quantity;

    public ItemSaveForm(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
