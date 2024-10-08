package whang.travel.domain.bulletinboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whang.travel.domain.image.Image;
import whang.travel.domain.image.ImageRepository;
import whang.travel.domain.image.ImageStore;
import whang.travel.domain.paging.Criteria;
import whang.travel.domain.paging.MemberPostCriteria;
import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;
import whang.travel.web.image.form.ImageNameForm;
import whang.travel.web.image.form.ImageSaveForm;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final ImageStore imageStore;

    @Override
    public Post save(SavePostForm post) throws IOException {
        Post savePost = postRepository.save(post);

        List<ImageNameForm> imageNameForms = imageStore.storeImages(post.getImages()); // 실제 이미지 저장
        log.info("들어온 이미지={}", imageNameForms);
        // 데이터베이스 저장.
        for (ImageNameForm imageNameForm : imageNameForms) {
            imageRepository.saveImage(new ImageSaveForm(imageNameForm.getOriginalName(), imageNameForm.getSaveName(), "post", savePost.getPostId()));
        }
        return savePost;
    }

    @Override
    public void update(Long postId, UpdatePostForm updateForm) {
        postRepository.updatePost(postId, updateForm);
    }

    @Override
    public List<Post> findPostByMemberId(MemberPostCriteria criteria) {
        return postRepository.findPostByMemberId(criteria);
    }

    @Override
    public Integer countMemberPosts(MemberPostCriteria criteria) {
        return postRepository.countMemberPosts(criteria);
    }

    // 게시물 상세 보기
    @Override
    public Optional<Post> findPostByPostId(Long postId) {
        Optional<Post> posts = postRepository.findPostByPostId(postId);

        // 이미지 찾아와서 넣어주기
        List<Image> imageList = imageRepository.findImages("post", postId);
        log.info("게시물 이미지 찾아오기={}", imageList);
        posts.ifPresent(post -> post.setImages(imageList));
        log.info("잘 담겼는지 확인={}", posts.get().getImages());
        return posts;
    }

    @Override
    public List<Post> findAllByPaging(Criteria criteria) {
        return postRepository.findAllByPaging(criteria);
    }

    @Override
    public Integer countPosts(Criteria criteria) {
        return postRepository.countPosts(criteria);
    }

    @Override
    public Map<String, Object> getPostList(int pageNum, int pageSize, String searchTitle) {
        return null;
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deletePost(postId);
    }

    @Override
    public List<DisplayPostForm> findAllWithMemberName(String searchTitle) {
        return postRepository.findAllWithMemberName(searchTitle);
    }
}
