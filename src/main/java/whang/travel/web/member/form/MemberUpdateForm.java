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
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String address;

    public MemberUpdateForm(String firstName, String lastName, String memberId, String password, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.address = address;
    }
}