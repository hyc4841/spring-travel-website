package whang.travel.web.member.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberEmailRequest {

    @Email
    @NotBlank
    private String email;
}
