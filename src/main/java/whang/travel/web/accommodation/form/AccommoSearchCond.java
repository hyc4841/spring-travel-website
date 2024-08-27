package whang.travel.web.accommodation.form;

import lombok.Data;

@Data
public class AccommoSearchCond {

    // 지역
    private String region;
    // 시작 날짜
    private String startDate;
    // 종료 날짜
    private String endDate;
    // 인원
    private Integer personnel;

}
