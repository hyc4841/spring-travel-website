package whang.travel;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whang.travel.domain.item.Item;
import whang.travel.domain.item.ItemRepository;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.domain.order.Order;
import whang.travel.domain.order.OrderService;
import whang.travel.web.item.form.ItemSaveForm;
import whang.travel.web.order.form.SaveOrderForm;

@Component
@RequiredArgsConstructor
public class TestData {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderService orderService;

    /**
     *  테스트용 데이터
     */
    @PostConstruct
    public void Init() {

        Member member = new Member();
        member.setFirstName("황");
        member.setLastName("윤철");
        member.setEmail("dbscjf4841@naver.com");
        member.setPassword("123");
        member.setMemberId("test");
        member.setAddress("서울시 은평구");
        memberRepository.save(member);

        ItemSaveForm itemA = new ItemSaveForm();
        itemA.setItemName("itemA");
        itemA.setPrice(1000);
        itemA.setQuantity(100);
        itemRepository.save(itemA);

        ItemSaveForm itemB = new ItemSaveForm();
        itemB.setItemName("itemB");
        itemB.setPrice(2000);
        itemB.setQuantity(200);
        itemRepository.save(itemB);

        ItemSaveForm itemC = new ItemSaveForm();
        itemC.setItemName("뭔데");
        itemC.setPrice(4000);
        itemC.setQuantity(100);
        itemRepository.save(itemC);

        ItemSaveForm itemD = new ItemSaveForm();
        itemD.setItemName("왜안되는데?");
        itemD.setPrice(5000);
        itemD.setQuantity(50);
        itemRepository.save(itemD);

        SaveOrderForm saveOrder1 = new SaveOrderForm();
        saveOrder1.setOrderMemberId("test");
        saveOrder1.setOrderItemId(1L);
        saveOrder1.setOrderItem("itemA");
        saveOrder1.setOrderItemPrice(1000);
        saveOrder1.setOrderItemQuantity(50);
        saveOrder1.setAddress("서울시 은평구");
        orderService.createOrder(saveOrder1);

        SaveOrderForm saveOrder2 = new SaveOrderForm();
        saveOrder2.setOrderMemberId("test");
        saveOrder2.setOrderItemId(2L);
        saveOrder2.setOrderItem("itemB");
        saveOrder2.setOrderItemPrice(2000);
        saveOrder2.setOrderItemQuantity(50);
        saveOrder2.setAddress("서울시 은평구");
        orderService.createOrder(saveOrder2);

        SaveOrderForm saveOrder3 = new SaveOrderForm();
        saveOrder3.setOrderMemberId("test");
        saveOrder3.setOrderItemId(3L);
        saveOrder3.setOrderItem("뭔데");
        saveOrder3.setOrderItemPrice(4000);
        saveOrder3.setOrderItemQuantity(50);
        saveOrder3.setAddress("서울시 은평구");
        orderService.createOrder(saveOrder3);

        SaveOrderForm saveOrder4 = new SaveOrderForm();
        saveOrder4.setOrderMemberId("test");
        saveOrder4.setOrderItemId(4L);
        saveOrder4.setOrderItem("왜안되는데?");
        saveOrder4.setOrderItemPrice(5000);
        saveOrder4.setOrderItemQuantity(50);
        saveOrder4.setAddress("서울시 은평구");
        orderService.createOrder(saveOrder4);


    }
}
