package whang.travel.domain.bulletinboard;

import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    // 게시판 글 저장소
    // 글 저장
    Post save(SavePostForm savePost);
    // 글 수정
    void updatePost(Long postId, UpdatePostForm editForm);
    // 글 맴버로 다중 조회
    List<Post> findPostByMemberId(Long memberId, String searchTitle);
    // 글 id로 단일 조회
    Optional<Post> findPostByPostId(Long postId);

    // 글 제목으로 검색
    List<Post> findAll(String searchTitle);

//    MemberName findMemberName(Long memberId);

    // 글 Id로 삭제
    void deletePost(Long postId);

    List<DisplayPostForm> findAllWithMemberName(String searchTitle);
}
