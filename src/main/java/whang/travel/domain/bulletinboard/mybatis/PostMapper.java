package whang.travel.domain.bulletinboard.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import whang.travel.domain.bulletinboard.Post;
import whang.travel.domain.bulletinboard.DisplayPostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

    // 글 저장
    void save(Post post);

    // 글 수정
    void update(@Param("postId") Long postId,@Param("updatePost") UpdatePostForm editForm);

    // 멤버 id로 글 다중 검색
    List<Post> findPostByMemberId(@Param("memberId") Long memberId,@Param("searchTitle") String searchTitle);

    // 글 id로 단건 조회
    Optional<Post> findPostByPostId(Long postId);


    List<Post> findAll(String searchTitle);

//    MemberName findMemberName(Long memberId);

    // 글 id로 삭제
    void deletePost(Long postId);

    List<DisplayPostForm> findAllWithMemberName(String searchTitle);



}
