package whang.travel.domain.bulletinboard;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import whang.travel.domain.image.Image;

import java.util.Date;
import java.util.List;

@Data
public class Post {

    // pk
    private Long postId;
    // 작성자(맴버 외래 키)
    private Long memberId;

    private String memberLoginId; // 멤버 로그인 id 보여주기용
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
    // 이미지
    private List<Image> images;

    public Post(Long memberId, String title, String content, String category, Date postDate) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.postDate = postDate;
    }
}
