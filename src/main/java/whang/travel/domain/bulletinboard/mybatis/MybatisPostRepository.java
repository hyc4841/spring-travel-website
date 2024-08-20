package whang.travel.domain.bulletinboard.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.domain.bulletinboard.Post;
import whang.travel.domain.bulletinboard.PostRepository;
import whang.travel.domain.bulletinboard.DisplayPostForm;
import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisPostRepository implements PostRepository {

    private final PostMapper postMapper;

    @Override
    public Post save(SavePostForm savePost) {

        Post post = new Post();
        post.setMemberId(savePost.getMemberId());
        post.setTitle(savePost.getTitle());
        post.setContent(savePost.getContent());
        post.setCategory(savePost.getCategory());
        post.setPostDate(new Date());

        postMapper.save(post);

        return post;
    }

    @Override
    public void updatePost(Long postId, UpdatePostForm updateForm) {
        log.info("글 업데이트={}", updateForm);

        // 수정 날짜는 repository에서
        updateForm.setEditDate(new Date());


        postMapper.update(postId, updateForm);
    }

    @Override
    public List<Post> findPostByMemberId(Long memberId, String searchTitle) {
        List<Post> postList = postMapper.findPostByMemberId(memberId, searchTitle);
        log.info("게시글 리스트={}", postList);
        return postList;
    }

    @Override
    public Optional<Post> findPostByPostId(Long postId) {
        Optional<Post> postList = postMapper.findPostByPostId(postId);
        log.info("게시글 리스트={}", postList);
        return postList;
    }

    @Override
    public List<Post> findAll(String searchTitle) {
        List<Post> postList = postMapper.findAll(searchTitle);
        log.info("게시글 리스트={}", postList);
        return postList;
    }

    /*
    @Override
    public MemberName findMemberName(Long memberId) {
        MemberName memberName = postMapper.findMemberName(memberId);
        log.info("작성자 이름 찾기={}", memberName);
        return memberName;
    }

     */

    @Override
    public void deletePost(Long postId) {
        postMapper.deletePost(postId);
    }

    @Override
    public List<DisplayPostForm> findAllWithMemberName(String searchTitle) {
        log.info("멤버id가 아니라 작성자 이름으로 찾기");
        return postMapper.findAllWithMemberName(searchTitle);
    }
}
