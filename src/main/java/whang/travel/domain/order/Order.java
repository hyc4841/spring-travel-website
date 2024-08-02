package whang.travel.domain.order;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private Long orderNum; // 주문 번호
    private String orderItem; // 주문한 상품
    private Integer orderItemPrice; // 주문한 상품의 가격
    private Integer orderItemQuantity; // 주문한 상품 수량
    private String orderMemberID; // 주문한 맴버
    private Date orderDate; // 주문 날짜
    private Boolean orderFinish; // 주문 완료 여부

    public Order() {
    }

    public Order(String orderItem, Integer orderItemPrice, Integer orderItemQuantity, String orderMemberID, Date orderDate, Boolean orderFinish) {
        this.orderItem = orderItem;
        this.orderItemPrice = orderItemPrice;
        this.orderItemQuantity = orderItemQuantity;
        this.orderMemberID = orderMemberID;
        this.orderDate = orderDate;
        this.orderFinish = orderFinish;
    }
}
