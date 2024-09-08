package whang.travel.web.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import whang.travel.domain.image.ImageStore;

import java.net.MalformedURLException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageStore imageStore;

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        log.info("이미지 다운로드={}", filename);
        return new UrlResource("file:" + imageStore.getFullPath(filename));
    }

}
