package whang.travel.domain.bulletinboard;

import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public interface PostService {

    Post save(SavePostForm post) throws IOException;

    void update(Long postId, UpdatePostForm editForm);

    List<Post> findPostByMemberId(Long memberId, String searchTitle);

    Optional<Post> findPostByPostId(Long postId);

    // 글 제목으로 검색
    List<Post> findAll(String searchTitle);

    Integer countPosts(String searchTitle);

    Map<String, Object> getPostList(int pageNum, int pageSize, String searchTitle);


//    MemberName findMemberName(Long memberId);

    void deletePost(Long postId);

    List<DisplayPostForm> findAllWithMemberName(String searchTitle);


}
