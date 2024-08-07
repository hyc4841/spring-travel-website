package whang.travel.domain.order;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private Long orderNum; // 주문 번호
    private String orderMemberID; // 주문한 맴버
    private Long orderItemId; // 주문 상품 id
    private String orderItem; // 주문한 상품
    private Integer orderItemPrice; // 주문한 상품의 가격
    private Integer orderItemQuantity; // 주문한 상품 수량
    private String address; // 주소
    private Date orderDate; // 주문 날짜
    private Integer totalPrice; // 총 주문 금액

    public Order() {
    }

    public Order(String orderMemberID, Long orderItemId, String orderItem, Integer orderItemPrice, Integer orderItemQuantity, String address, Date orderDate, Integer totalPrice) {
        this.orderMemberID = orderMemberID;
        this.orderItemId = orderItemId;
        this.orderItem = orderItem;
        this.orderItemPrice = orderItemPrice;
        this.orderItemQuantity = orderItemQuantity;
        this.address = address;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }
}
