package whang.travel.web.bulletinBoard.form;

import lombok.Data;

import java.util.Date;

@Data
public class UpdatePostForm {

    // 게시글 수정할 때 사용할 데이터 형식
    private Long postId;
    // 수정 제목
    private String editTitle;
    // 수정 내용
    private String editContent;
    // 수정 카테고리
    private String editCategory;

    private Long memberId;
    // 최종 수정 일자
    private Date editDate;


}
