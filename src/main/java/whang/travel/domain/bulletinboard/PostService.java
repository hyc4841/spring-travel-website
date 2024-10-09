package whang.travel.domain.bulletinboard;

import whang.travel.domain.paging.bulletinboard.Criteria;
import whang.travel.domain.paging.profilePost.MemberPostCriteria;
import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PostService {

    Post save(SavePostForm post) throws IOException;

    void update(Long postId, UpdatePostForm editForm);

    List<Post> findPostByMemberId(MemberPostCriteria criteria);

    Integer countMemberPosts(MemberPostCriteria criteria);


    Optional<Post> findPostByPostId(Long postId);

    List<Post> findAllByPaging(Criteria criteria);

    Integer countPosts(Criteria criteria);

    Map<String, Object> getPostList(int pageNum, int pageSize, String searchTitle);

    void deletePost(Long postId);

    List<DisplayPostForm> findAllWithMemberName(String searchTitle);


}
