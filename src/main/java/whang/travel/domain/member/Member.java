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


    private String role; // user 및 admin 등등

    public Member(String firstName, String lastName, String memberId, String password, String email, String address, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.address = address;
        this.role = role;
    }

}
