package whang.travel.web.bulletinBoard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import whang.travel.domain.bulletinboard.Post;
import whang.travel.domain.bulletinboard.PostService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bulletinBoard")
public class BulletinBoardController {

    private final PostService postService;

    @GetMapping("/list") // 게시판 목록
    String bulletinBoardList(@ModelAttribute("searchTitle") String searchTitle, Model model) {
        List<Post> postList = postService.findAll(searchTitle);

        model.addAttribute("posts", postList);
        return "/bulletinboard/list";
    }

    @GetMapping("/add") // 글 작성 화면으로
    String postAddForm() {


        return "bulletinboard/add";
    }


}
