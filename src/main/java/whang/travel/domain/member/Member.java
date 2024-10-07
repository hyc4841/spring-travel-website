package whang.travel.domain.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Member {

    private Long id;
    @NotBlank(message = "공백이면 안됩니다. 성을 제대로 입력해주세요!")
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

    private String role; // user 및 admin 등등
    @NotBlank
    private String number;
    @NotNull
    private LocalDate birth;
    @NotBlank
    private String sex;

}
