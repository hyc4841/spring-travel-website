package whang.travel.domain.bulletinboard;

import lombok.Data;

import java.util.Date;

@Data
public class Post {

    // pk
    private Long postId;
    // 작성자(맴버 외래 키)
    private Long memberId;
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

}
