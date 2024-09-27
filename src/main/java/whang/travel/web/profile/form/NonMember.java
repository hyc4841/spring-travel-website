package whang.travel.web.profile.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NonMember {

    // 비회원 예약 내역 확인 시 필요한 정보
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}
