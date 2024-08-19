package whang.travel.domain.bulletinboard;

import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post save(SavePostForm post);

    void Update(Long postId, UpdatePostForm editForm);

    List<Post> findPostByMemberId(Long memberId, String searchTitle);

    Optional<Post> findPostByPostId(Long postId);

    // 글 제목으로 검색
    List<Post> findAll(String searchTitle);

//    MemberName findMemberName(Long memberId);

    void deletePost(Long postId);

    List<DisplayPostForm> findAllWithMemberName(String searchTitle);


}
