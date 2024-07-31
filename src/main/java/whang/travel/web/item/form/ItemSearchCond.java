package whang.travel.web.item.form;

import lombok.Data;

@Data
public class ItemSearchCond {

    // 검색 조건
    // 이름으로 검색하기
    // 가격으로 검색하기

    private String itemName;
    private Integer maxPrice;

    public ItemSearchCond() {
    }

    public ItemSearchCond(String itemName, Integer maxPrice) {
        this.itemName = itemName;
        this.maxPrice = maxPrice;
    }
}
