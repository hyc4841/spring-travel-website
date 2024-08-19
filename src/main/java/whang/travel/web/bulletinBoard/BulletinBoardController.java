package whang.travel.web.bulletinBoard;

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
import whang.travel.web.bulletinBoard.form.SavePostForm;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bulletinBoard")
public class BulletinBoardController {

    private final PostService postService;

    @GetMapping("/list")
        // 게시판 목록
    String bulletinBoardList(@ModelAttribute("searchTitle") String searchTitle, Model model) {
        List<DisplayPostForm> postList = postService.findAllWithMemberName(searchTitle);


        model.addAttribute("posts", postList);
        return "/bulletinboard/postListForm";
    }

    @GetMapping("/add")
        // 글 작성 화면으로
    String postAddForm(@ModelAttribute("post") SavePostForm post) {

        return "bulletinboard/postAddForm";
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
