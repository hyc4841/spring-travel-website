package whang.travel.web.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    // 숙소 검색
    // 숙소 검색은 지역 이름이 들어오면 지역 기반으로, 숙소 이름으로 들어오면 이름 기반으로 지역인지 숙소 이름인지는 간단함. 지역명 ex) 부산, 서울, 강릉 이 들어오면
    // 지역명이 들어온 것으로 인식하고 지역 기반 검색으로 하면 됨.


    // 숙소 리스트

    // 숙소 상세 화면
}
