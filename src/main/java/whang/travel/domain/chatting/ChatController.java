package whang.travel.domain.chatting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/chatting")
    public String chatting() {

        return "/chatting/chat";
    }


    @MessageMapping("/send")
    public void sendMessage(String message) {
        // 그럼 검열도 여기서 가능하겠다
        log.info("메시지={}", message);
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}
