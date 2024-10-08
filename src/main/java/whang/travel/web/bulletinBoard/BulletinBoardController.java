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
import whang.travel.domain.bulletinboard.Criteria;
import whang.travel.domain.bulletinboard.Post;
import whang.travel.domain.bulletinboard.PostService;
import whang.travel.domain.bulletinboard.DisplayPostForm;
import whang.travel.domain.member.Member;
import whang.travel.domain.member.MemberRepository;
import whang.travel.web.bulletinBoard.form.PageMaker;
import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.bulletinBoard.form.UpdatePostForm;
import whang.travel.web.bulletinBoard.form.SearchForm;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bulletinBoard")
public class BulletinBoardController {

    private final PostService postService;
    private final MemberRepository memberRepository; // 바로 repository에 접근하는게 맞는 걸까?

    @GetMapping("/list")
    // 게시판 목록 화면
    public String bulletinBoardList(@ModelAttribute("criteria") Criteria criteria, @AuthenticationPrincipal UserDetails user,
                                    Model model) {

        List<Post> postList = postService.findAllByPaging(criteria);

        Integer total = postService.countPosts(criteria);

        PageMaker pageMaker = new PageMaker(criteria, total);

        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("posts", postList);
        model.addAttribute("user", user);

        return "/bulletinboard/postListForm";
    }

    @GetMapping("/add") // 글 작성 화면으로
    public String postAddForm(@ModelAttribute("post") SavePostForm post, @ModelAttribute("criteria") Criteria criteria,
                              @AuthenticationPrincipal UserDetails user,
                              Model model) {

        // 현재 로그인한 사용자 찾기
        Member member = findMember(user);
        post.setMemberLoginId(member.getMemberId());

        model.addAttribute("user", user);

        return "bulletinboard/postAddForm";
    }

    // 현재 로그인한 유저의 id를 찾기 위한 메소드


    @PostMapping("/add")
    public String postAdd(@Validated @ModelAttribute("post") SavePostForm post, BindingResult bindingResult,
                          @ModelAttribute("criteria") Criteria criteria,
                          RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            log.info("오류 발생={}", bindingResult);
            return "bulletinboard/postAddForm";
        }

        // 멤버의 loginId로 id 찾기
        Long id = memberRepository.findIdByLoginId(post.getMemberLoginId());
        post.setMemberId(id);
        postService.save(post);

        redirectAttributes.addAttribute("pageNum", criteria.getPageNum());
        redirectAttributes.addAttribute("amount", criteria.getAmount());
        redirectAttributes.addAttribute("keyword", criteria.getKeyword());

        return "redirect:/bulletinBoard/list";
    }


    // 게시판 글 보여줄 화면
    @GetMapping("/detail/{postId}")
    public String showPost(@PathVariable Long postId, @ModelAttribute("criteria") Criteria criteria,
                           @AuthenticationPrincipal UserDetails user, Model model) {
        // 게시물을 가져온다.
        Post post = postService.findPostByPostId(postId).get(); // 서비스 단계에서 이미지도 찾아온다

        // 작성자를 멤버 로그인 id로 보여주기.
        Member member = memberRepository.findById(post.getMemberId()).get();
        String memberId = member.getMemberId();
        post.setMemberLoginId(memberId);

        model.addAttribute("post", post);
        model.addAttribute("user", user);

        return "/bulletinboard/postDetailForm";
    }

    // 글 수정 화면
    @GetMapping("/edit/{postId}")
    public String updatePostForm(@PathVariable Long postId, @ModelAttribute("criteria") Criteria criteria,
                                 @AuthenticationPrincipal UserDetails user, Model model) {

        Post post = postService.findPostByPostId(postId).get();
        Member member = memberRepository.findById(post.getMemberId()).get();

        UpdatePostForm updatePost =
                new UpdatePostForm(post.getPostId(), member.getMemberId(), post.getTitle(), post.getContent(), post.getCategory(), post.getMemberId());

        model.addAttribute("updatePost", updatePost);
        model.addAttribute("user", user);

        return "/bulletinboard/postUpdateForm";
    }

    @PostMapping("/edit/{postId}")
    public String updatePost(@Validated @ModelAttribute("updatePost") UpdatePostForm updatePost, BindingResult bindingResult,
                             @ModelAttribute("criteria") Criteria criteria,
                             RedirectAttributes redirectAttributes, @PathVariable Long postId) {

        if (bindingResult.hasErrors()) {
            log.info("글 수정 오류={}", bindingResult);
            return "/bulletinboard/postUpdateForm"; // 오류와 함께 다시 글 수정 화면으로
        }
        Long memberId = memberRepository.findIdByLoginId(updatePost.getMemberLoginId());
        updatePost.setMemberId(memberId);

        postService.update(postId, updatePost);

        redirectAttributes.addAttribute("postId", postId);

        // url 파라미터로 보내려면 이렇게 해야한다고 함.
        redirectAttributes.addAttribute("pageNum", criteria.getPageNum());
        redirectAttributes.addAttribute("amount", criteria.getAmount());
        redirectAttributes.addAttribute("keyword", criteria.getKeyword());

        return "redirect:/bulletinBoard/detail/{postId}";
    }

    // 게시글 삭제하기 기능 추가하기
    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId, @ModelAttribute("criteria") Criteria criteria,
                             RedirectAttributes redirectAttributes) {

        postService.deletePost(postId);

        // 파라미터
        redirectAttributes.addAttribute("pageNum", criteria.getPageNum());
        redirectAttributes.addAttribute("amount", criteria.getAmount());
        redirectAttributes.addAttribute("keyword", criteria.getKeyword());

        return "redirect:/bulletinBoard/list";
    }

    private Member findMember(@AuthenticationPrincipal UserDetails curMember) {
        String username = curMember.getUsername();
        Member member = memberRepository.findByLoginId(username).get();
        return member;
    }

}
