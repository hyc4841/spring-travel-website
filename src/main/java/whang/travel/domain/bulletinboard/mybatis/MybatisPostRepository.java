package whang.travel.domain.bulletinboard.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import whang.travel.domain.bulletinboard.Post;
import whang.travel.domain.bulletinboard.PostRepository;
import whang.travel.web.bulletinBoard.form.EditPostForm;
import whang.travel.web.member.form.MemberName;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisPostRepository implements PostRepository {


    private final PostMapper postMapper;

    @Override
    public Post save(Post savePost) {
        postMapper.save(savePost);

        return savePost;
    }

    @Override
    public void updatePost(Long postId, EditPostForm editForm) {
        log.info("글 업데이트={}", editForm);
        postMapper.update(postId, editForm);
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

    @Override
    public MemberName findMemberName(Long memberId) {
        MemberName memberName = postMapper.findMemberName(memberId);
        log.info("작성자 이름 찾기={}", memberName);
        return memberName;
    }

    @Override
    public void deletePost(Long postId) {
        postMapper.deletePost(postId);
    }
}
