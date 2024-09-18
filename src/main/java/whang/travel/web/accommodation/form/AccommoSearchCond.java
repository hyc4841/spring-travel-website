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
    private String checkIn;
    // 종료 날짜
    @NotBlank
    private String checkOut;
    // 인원
    @NotNull
    private Integer personnel;

}
