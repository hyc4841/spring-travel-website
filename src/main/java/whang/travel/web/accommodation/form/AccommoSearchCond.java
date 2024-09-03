package whang.travel.web.accommodation.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccommoSearchCond {

    // 지역
    @NotBlank
    private String region;
    // 시작 날짜
    @NotBlank
    private String startDate;
    // 종료 날짜
    @NotBlank
    private String endDate;
    // 인원
    @NotNull
    private Integer personnel;

}
