package whang.travel.domain.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Member {

    private Long id;

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

}
