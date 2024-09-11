package whang.travel.web.bulletinBoard.form;

import lombok.Data;

import java.util.Date;

@Data
public class UpdatePostForm {

    private Long postId; // 게시글 수정할 때 사용할 데이터 형식

    private String memberLoginId; // 수정화면에서 멤버의 로그인id를 보여주기 위해

    private String editTitle; // 수정 제목
    private String editContent; // 수정 내용
    private String editCategory; // 수정 카테고리
    private Long memberId;
    private Date editDate; // 최종 수정 일자


    public UpdatePostForm(Long postId, String memberLoginId, String editTitle, String editContent, String editCategory, Long memberId) {
        this.postId = postId;
        this.memberLoginId = memberLoginId;
        this.editTitle = editTitle;
        this.editContent = editContent;
        this.editCategory = editCategory;
        this.memberId = memberId;
    }
}
