package whang.travel.web.bulletinBoard;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import whang.travel.domain.bulletinboard.Post;
import whang.travel.domain.bulletinboard.PostService;
import whang.travel.domain.bulletinboard.DisplayPostForm;
import whang.travel.domain.member.Member;
import whang.travel.web.SessionConst;
import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;
import whang.travel.web.search.form.SearchForm;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bulletinBoard")
public class BulletinBoardController {

    private final PostService postService;

    @GetMapping("/list")
    // 게시판 목록
    public String bulletinBoardList(@ModelAttribute("searchForm") SearchForm searchForm, Model model) {
        List<DisplayPostForm> postList = postService.findAllWithMemberName(searchForm.getSearchTitle());
        log.info("제목 검색 데이터={}", searchForm);
        model.addAttribute("posts", postList);

        return "/bulletinboard/postListForm";
    }

    @GetMapping("/add")
    // 글 작성 화면으로
    public String postAddForm(@ModelAttribute("post") SavePostForm post, HttpServletRequest request, Model model) {

        // 작성자의 멤버 id를 찾아와야 한다. 현재 로그인한 사용자 찾기
        Long loginMemberId = getId(request);
        post.setMemberId(loginMemberId);

        return "bulletinboard/postAddForm";
    }

    // 현재 로그인한 유저의 id를 찾기 위한 메소드
    private static Long getId(HttpServletRequest request) {
        // 현재 로그인한 멤버 찾기
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        return loginMember.getId();
    }

    @PostMapping("/add")
    public String postAdd(@Validated @ModelAttribute("post") SavePostForm post, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("오류 발생={}", bindingResult);
            return "bulletinboard/postAddForm";
        }
        postService.save(post);

        return "redirect:/bulletinBoard/list";
    }


    // 게시판 글 보여줄 화면
    @GetMapping("/detail/{postId}")
    public String showPost(@PathVariable Long postId, Model model) {
        // 게시물을 가져온다.
        Post post = postService.findPostByPostId(postId).get();
        // DB접근해서 게시 글을 가져와서 보여준다
        // 수정 화면에서도 DB에 접근해서 가져와서 보여주는 식으로 하자

        model.addAttribute("post", post);

        return "/bulletinboard/postDetailForm";
    }

    // 글 수정 화면
    @GetMapping("/edit/{postId}")
    public String updatePostForm(@PathVariable Long postId, Model model) {
        Post post = postService.findPostByPostId(postId).get();

        UpdatePostForm updatePost = new UpdatePostForm();
        updatePost.setEditTitle(post.getTitle());
        updatePost.setEditContent(post.getContent());
        updatePost.setEditCategory(post.getCategory());
        updatePost.setMemberId(post.getMemberId());
        updatePost.setPostId(post.getPostId());

        model.addAttribute("updatePost", updatePost);

        return "/bulletinboard/postUpdateForm";
    }

    @PostMapping("/edit/{postId}")
    public String updatePost(@Validated @ModelAttribute("updatePost") UpdatePostForm updatePost, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, @PathVariable Long postId) {

        if (bindingResult.hasErrors()) {
            log.info("글 수정 오류={}", bindingResult);

            return "/bulletinboard/postUpdateForm"; // 오류와 함께 다시 글 수정 화면으로
        }

        postService.Update(postId, updatePost);

        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/bulletinBoard/detail/{postId}";
    }
}
