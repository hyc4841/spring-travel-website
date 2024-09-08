package whang.travel.web.accommodation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import whang.travel.domain.accommodation.Accommodation;
import whang.travel.domain.accommodation.AccommodationService;
import whang.travel.domain.accommodation.ServiceList;
import whang.travel.domain.image.Image;
import whang.travel.domain.image.ImageRepository;
import whang.travel.domain.image.ImageStore;
import whang.travel.web.accommodation.form.AccommoSearchCond;

import java.net.MalformedURLException;
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
    private final ImageStore imageStore;
    private final ImageRepository imageRepository;

    // img test

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

        // 숙소 소개용 사진 이름을 보내줘야함
        List<Image> accommoImages = imageRepository.findImages("accommodation", accommoId);
        log.info("이미지 찾기={}", accommoImages);

        Accommodation accommodation = accommoService.findAccommoById(accommoId).get();
        log.info("숙소 검색={}", accommodation);

        // service는 값이 여러개 여서 파싱 해서 분리해줘야함.
        String[] service = parseService(accommodation.getService());


        // 메인 페이지에 이미지 5개 보내기. 다른 방법을 찾아보자
        model.addAttribute("Image1", accommoImages.get(0));
        model.addAttribute("Image2", accommoImages.get(1));
        model.addAttribute("Image3", accommoImages.get(2));
        model.addAttribute("Image4", accommoImages.get(3));
        model.addAttribute("Image5", accommoImages.get(4));

        model.addAttribute("icons", ServiceList.values());
        model.addAttribute("service", service);
        model.addAttribute("accommodation", accommodation);
        model.addAttribute("user", user);

        return "/accommodation/accommoDetail";
    }

//    @ResponseBody
//    @GetMapping("/images/{filename}")
//    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
//        log.info("이미지 다운로드={}", filename);
//        return new UrlResource("file:" + imageStore.getFullPath(filename));
//    }


    // service 파싱 함수
    private String[] parseService(Object[] service) {
        String data = (String) service[0];
        data = data.replaceAll("\\[\\[|\\]\\]|\\s+", "");
        return data.split(",");
    }



}

