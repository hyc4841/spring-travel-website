package whang.travel.web.bulletinBoard;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import whang.travel.domain.bulletinboard.PostService;
import whang.travel.domain.bulletinboard.DisplayPostForm;
import whang.travel.domain.member.Member;
import whang.travel.web.SessionConst;
import whang.travel.web.bulletinBoard.form.SavePostForm;
import whang.travel.web.search.form.SearchForm;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bulletinBoard")
public class BulletinBoardController {

    private final PostService postService;

    @GetMapping("/list") // 게시판 목록
    String bulletinBoardList(@ModelAttribute("searchForm") SearchForm searchForm, Model model) {
        List<DisplayPostForm> postList = postService.findAllWithMemberName(searchForm.getSearchTitle());
        log.info("제목 검색 데이터={}", searchForm);
        model.addAttribute("posts", postList);

        return "/bulletinboard/postListForm";
    }

    @GetMapping("/add")
        // 글 작성 화면으로
    String postAddForm(@ModelAttribute("post") SavePostForm post, HttpServletRequest request, Model model) {

        // 작성자의 멤버 id를 찾아와야 한다. 현재 로그인한 사용자 찾기
        Long loginMemberId = getMemberId(request);
        post.setMemberId(loginMemberId);

        return "bulletinboard/postAddForm";
    }

    private static Long getMemberId(HttpServletRequest request) {
        // 현재 로그인한 멤버 찾기
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long loginMemberId = loginMember.getId();

        return loginMemberId;
    }

    @PostMapping("/add")
    String postAdd(@Validated @ModelAttribute("post") SavePostForm post, BindingResult bindingResult,
                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("오류 발생={}", bindingResult);
            return "bulletinboard/postAddForm";
        }

        postService.save(post);

        return "redirect:/bulletinboard/postListForm";
    }
}
