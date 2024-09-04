package whang.travel.web.accommodation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import whang.travel.domain.accommodation.Accommodation;
import whang.travel.domain.accommodation.AccommodationService;
import whang.travel.web.accommodation.form.AccommoSearchCond;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/accommodation")
@RequiredArgsConstructor
public class AccommodationController {
    // 숙소 검색
    // 숙소 검색은 지역 이름이 들어오면 지역 기반으로, 숙소 이름으로 들어오면 이름 기반으로 지역인지 숙소 이름인지는 간단함. 지역명 ex) 부산, 서울, 강릉 이 들어오면
    // 지역명이 들어온 것으로 인식하고 지역 기반 검색으로 하면 됨.

    private final AccommodationService accommoService;

    @GetMapping("/list")
    public String AccommoList(@Validated @ModelAttribute("searchCond") AccommoSearchCond accommoSearchCond,
                              BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails user) {

        if (bindingResult.hasErrors()) {
            log.info("에러 발생={}", bindingResult);
            return "/home/homepage";
        }

        // 넘겨야할 데이터 : 숙소 분류(category), 숙소 이름, 숙소 지역, 리뷰 개수, 별점, 썸네일 이미지

        List<Accommodation> accommoList = accommoService.findAccommoList(accommoSearchCond);

        model.addAttribute("user", user);
        model.addAttribute("accommoList", accommoList);

        return "/accommodation/accommoSearchList";
    }

    @GetMapping("/detail/{accommoId}")
    public String AccommoDetail(@PathVariable Long accommoId, @AuthenticationPrincipal UserDetails user, Model model) {

        Accommodation accommodation = accommoService.findAccommoById(accommoId).get();

        log.info("숙소 검색={}", accommodation);

        model.addAttribute("accommodation", accommodation);
        model.addAttribute("user", user);

        return "/accommodation/accommoDetail";
    }


    // 숙소 리스트

    // 숙소 상세 화면
}
