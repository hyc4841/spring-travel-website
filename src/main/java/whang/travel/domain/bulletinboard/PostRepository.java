package whang.travel.domain.bulletinboard;

import whang.travel.domain.paging.bulletinboard.Criteria;
import whang.travel.domain.paging.profilePost.MemberPostCriteria;
import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.bulletinBoard.form.ShowPostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;

import java.util.List;
import java.util.Optional;

public interface  PostRepository {
    // 게시판 글 저장소
    // 글 저장
    Post save(SavePostForm savePost);
    // 글 수정
    void updatePost(Long postId, UpdatePostForm editForm);
    // 글 맴버로 다중 조회
    List<Post> findPostByMemberId(MemberPostCriteria criteria);

    Integer countMemberPosts(MemberPostCriteria criteria);

    // 글 id로 단일 조회
    Optional<Post> findPostByPostId(Long postId);

    // 페이징 게시물 검색
    List<ShowPostForm> findAllByPaging(Criteria criteria);

    // 총 게시물의 갯수
    Integer countPosts(Criteria criteria);

    // 글 Id로 삭제
    void deletePost(Long postId);

    List<DisplayPostForm> findAllWithMemberName(String searchTitle);
}
