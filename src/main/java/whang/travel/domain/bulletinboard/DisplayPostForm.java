package whang.travel.domain.bulletinboard;

import lombok.Data;

import java.util.Date;

@Data
public class DisplayPostForm {

    // pk
    private Long postId;
    // 작성자
    private String firstName;
    private String lastName;
    // 제목
    private String title; // 100자
    // 내용
    private String content; // 10000자
    // 카테고리
    private String category;
    // 작성 일자
    private Date postDate;
    // 최종 수정 일자
    private Date postEditDate;

    public DisplayPostForm(String fistName, String lastName, String title, String content, String category, Date postDate) {
        this.firstName = fistName;
        this.lastName = lastName;
        this.title = title;
        this.content = content;
        this.category = category;
        this.postDate = postDate;
    }
}
