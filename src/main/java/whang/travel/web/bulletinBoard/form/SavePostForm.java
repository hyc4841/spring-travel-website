package whang.travel.web.bulletinBoard.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SavePostForm {

    @NotNull
    private Long memberId;

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
