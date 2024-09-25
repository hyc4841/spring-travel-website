package whang.travel.web.member.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberUpdateForm {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String memberId;
    @NotBlank
    private String email;
    @NotBlank
    private String address;
}
