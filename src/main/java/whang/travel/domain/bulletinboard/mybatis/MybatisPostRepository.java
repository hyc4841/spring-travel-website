package whang.travel.domain.bulletinboard.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.domain.paging.Criteria;
import whang.travel.domain.bulletinboard.Post;
import whang.travel.domain.bulletinboard.PostRepository;
import whang.travel.domain.bulletinboard.DisplayPostForm;
import whang.travel.domain.paging.MemberPostCriteria;
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
        Post post = new Post(savePost.getMemberId(), savePost.getTitle(), savePost.getContent(), savePost.getCategory(), new Date());
        postMapper.save(post);
        return post;
    }

    @Override
    public void updatePost(Long postId, UpdatePostForm updateForm) {
        log.info("updatePost={}", updateForm);

        // 수정 날짜는 repository에서
        updateForm.setEditDate(new Date());

        postMapper.update(postId, updateForm);
    }

    @Override
    public List<Post> findPostByMemberId(MemberPostCriteria criteria) {
        List<Post> postList = postMapper.findPostByMemberId(criteria);
        log.info("findPostByMemberId={}", postList);
        return postList;
    }

    @Override
    public Integer countMemberPosts(MemberPostCriteria criteria) {
        return postMapper.countMemberPosts(criteria);
    }

    @Override
    public Optional<Post> findPostByPostId(Long postId) {
        Optional<Post> postList = postMapper.findPostByPostId(postId);
        log.info("findPostByPostId={}", postList);
        return postList;
    }

    @Override
    public List<Post> findAllByPaging(Criteria criteria) {
        return postMapper.findAllByPaging(criteria);
    }

    @Override
    public Integer countPosts(Criteria criteria) {
        return postMapper.countPosts(criteria);
    }

    @Override
    public void deletePost(Long postId) {
        postMapper.deletePost(postId);
    }

    @Override
    public List<DisplayPostForm> findAllWithMemberName(String searchTitle) {
        List<DisplayPostForm> posts = postMapper.findAllWithMemberName(searchTitle);
        log.info("findAllWithMemberName={}", posts);

        return posts;
    }
}
