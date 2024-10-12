package whang.travel.web.bulletinBoard.form;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ShowPostForm {
    // 게시판 리스트에서 보여주기 용.
    private Long postId;
    private String memberId;
    private String title;
    private String category;
    private LocalDateTime postDate;
}