package whang.travel.web.bulletinBoard.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SavePostForm {

    private Long memberId; // 멤버의 로그인 id가 아니라 DB상 id를 말함

    private String memberLoginId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String category;

    public SavePostForm(Long memberId, String title, String content, String category) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
