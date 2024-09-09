package whang.travel.web.bulletinBoard;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import whang.travel.domain.bulletinboard.Post;
import whang.travel.domain.bulletinboard.PostService;
import whang.travel.domain.bulletinboard.DisplayPostForm;
import whang.travel.domain.image.ImageRepository;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;
import whang.travel.web.search.form.SearchForm;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bulletinBoard")
public class BulletinBoardController {

    private final PostService postService;
    private final MemberRepository memberRepository;

    @GetMapping("/list")
    // 게시판 목록 화면
    public String bulletinBoardList(@ModelAttribute("searchForm") SearchForm searchForm, @AuthenticationPrincipal UserDetails user, Model model) {
        List<DisplayPostForm> postList = postService.findAllWithMemberName(searchForm.getSearchTitle());
        log.info("제목 검색 데이터={}", searchForm);
        model.addAttribute("posts", postList);
        model.addAttribute("user", user);

        return "/bulletinboard/postListForm";
    }

    @GetMapping("/add") // 글 작성 화면으로
    public String postAddForm(@ModelAttribute("post") SavePostForm post, @AuthenticationPrincipal UserDetails user,
                              HttpServletRequest request, Model model) {

        // 현재 로그인한 사용자 찾기
        Member member = findMember(request, user);
        post.setMemberLoginId(member.getMemberId());

        model.addAttribute("user", user);

        return "bulletinboard/postAddForm";
    }

    // 현재 로그인한 유저의 id를 찾기 위한 메소드
    private Member findMember(HttpServletRequest request, @AuthenticationPrincipal UserDetails curMember) {
        String username = curMember.getUsername();
        Member member = memberRepository.findByLoginId(username).get();
        return member;
        /*
        spring security 도입하면서 이 부분은 사용이 불가능해짐
        // 현재 로그인한 멤버 찾기
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        return loginMember.getId();
         */
    }

    @PostMapping("/add")
    public String postAdd(@Validated @ModelAttribute("post") SavePostForm post, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            log.info("오류 발생={}", bindingResult);
            return "bulletinboard/postAddForm";
        }

        // 멤버의 loginId로 id 찾기
        Long id = memberRepository.findIdByLoginId(post.getMemberLoginId());
        post.setMemberId(id);
        postService.save(post);

        return "redirect:/bulletinBoard/list";
    }


    // 게시판 글 보여줄 화면
    @GetMapping("/detail/{postId}")
    public String showPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetails user, Model model) {
        // 게시물을 가져온다.
        Post post = postService.findPostByPostId(postId).get(); // 서비스 단계에서 이미지도 찾아온다

        model.addAttribute("post", post);
        model.addAttribute("user", user);

        return "/bulletinboard/postDetailForm";
    }

    // 글 수정 화면
    @GetMapping("/edit/{postId}")
    public String updatePostForm(@PathVariable Long postId, @AuthenticationPrincipal UserDetails user, Model model) {
        Post post = postService.findPostByPostId(postId).get();

        UpdatePostForm updatePost = new UpdatePostForm();
        updatePost.setEditTitle(post.getTitle());
        updatePost.setEditContent(post.getContent());
        updatePost.setEditCategory(post.getCategory());
        updatePost.setMemberId(post.getMemberId());
        updatePost.setPostId(post.getPostId());

        model.addAttribute("updatePost", updatePost);
        model.addAttribute("user", user);

        return "/bulletinboard/postUpdateForm";
    }

    @PostMapping("/edit/{postId}")
    public String updatePost(@Validated @ModelAttribute("updatePost") UpdatePostForm updatePost, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, @PathVariable Long postId) {

        if (bindingResult.hasErrors()) {
            log.info("글 수정 오류={}", bindingResult);

            return "/bulletinboard/postUpdateForm"; // 오류와 함께 다시 글 수정 화면으로
        }

        postService.update(postId, updatePost);

        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/bulletinBoard/detail/{postId}";
    }

    // 게시글 삭제하기 기능 추가하기

}
