package whang.travel.domain.bulletinboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post save(SavePostForm post) {
        return postRepository.save(post);
    }

    @Override
    public void update(Long postId, UpdatePostForm updateForm) {
        postRepository.updatePost(postId, updateForm);
    }

    @Override
    public List<Post> findPostByMemberId(Long memberId, String searchTitle) {
        return postRepository.findPostByMemberId(memberId, searchTitle);
    }

    @Override
    public Optional<Post> findPostByPostId(Long postId) {
        return postRepository.findPostByPostId(postId);
    }

    @Override
    public List<Post> findAll(String searchTitle) {
        return postRepository.findAll(searchTitle);
    }

    /*
    @Override
    public MemberName findMemberName(Long memberId) {
        return postRepository.findMemberName(memberId);
    }

     */

    @Override
    public void deletePost(Long postId) {
        postRepository.deletePost(postId);
    }

    @Override
    public List<DisplayPostForm> findAllWithMemberName(String searchTitle) {
        return postRepository.findAllWithMemberName(searchTitle);
    }
}
