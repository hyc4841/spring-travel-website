package whang.travel.domain.item;

import lombok.Data;

@Data
public class Item {
    // 아이템 등록 번호
    // 아이템 이름
    // 아이템 수량
    // 아이템 가격
    // 아이템 분류 (공구, 생활 가전, 등등)
    // 판매자
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
