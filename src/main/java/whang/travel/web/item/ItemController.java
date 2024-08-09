package whang.travel.web.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import whang.travel.domain.item.Item;
import whang.travel.domain.item.ItemService;
import whang.travel.web.item.form.ItemSaveForm;
import whang.travel.web.item.form.ItemSearchCond;
import whang.travel.web.item.form.ItemUpdateForm;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String itemHome() {
        return "redirect:/item/items";
    }

    @GetMapping("/{itemId}") // 아이템 상세 페이지
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemService.findById(itemId).get(); // Optional 사용할 때 주의하자...
        // Optional<Item> item = itemService.findById(itemId); 이렇게 하면 밑에서 못읽어 들임. 그래서 위에 처럼 get()써서 Item으로 만들어놔야함.
        model.addAttribute("item", item);
        return "/items/item";
    }

    @GetMapping("/items") // 아이템 목록 폼. 조건 검색을 할 수 있도록 만듬
    public String items(@ModelAttribute("searchCond") ItemSearchCond cond, Model model) {
        List<Item> items = itemService.findAll(cond);
        model.addAttribute("items", items);
        return "/items/items";
    }

    @GetMapping("/add") // 아이템 추가 폼으로 이동
    public String addItemForm(@ModelAttribute("itemSaveForm") ItemSaveForm itemSaveForm) {

        return "/items/addForm";
    }

    @PostMapping("/add") // 아이템 추가하기
    public String addItem(@Validated @ModelAttribute("itemSaveForm") ItemSaveForm item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "/items/addForm";
        }

        Item saveitem = itemService.save(item);
        redirectAttributes.addAttribute("itemId", saveitem.getItemId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/item/{itemId}"; // 아이템 저장을 완료하면 아이템 상세 페이지로 리다이렉트 하도록 만듬
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findById(itemId).get();
        model.addAttribute("itemUpdateForm", item);
        return "/items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@Validated @ModelAttribute("itemUpdateForm") ItemUpdateForm updateForm, BindingResult bindingResult, @PathVariable Long itemId,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "/items/editForm";
        }

        itemService.update(itemId, updateForm);

        redirectAttributes.addAttribute("itemId", itemId);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/item/{itemId}";
    }

}