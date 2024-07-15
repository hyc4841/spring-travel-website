package whang.travel.web.session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 세션 관리
 */
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "loginSessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>(); // 세션은 세션을 요청한 객체와 무작위 세션 id가 담긴다.

    /**
     * 세션 생성
     */
    public void createSession(Object value, HttpServletResponse response) {

        String sessionId = UUID.randomUUID().toString(); // 무작위 값으로 세션 ID 생성
        sessionStore.put(sessionId, value);              // 세션 저장소에 저장

        Cookie loginSessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);// 만들고자 하는 세션의 이름과 값으로 생성
        response.addCookie(loginSessionCookie);
    }

    /**
     * 세션 조회
     */
    public Object getSession(HttpServletRequest request) { // 쿠키 가져오기
        Cookie loginSessionCookie = findCookie(request, SESSION_COOKIE_NAME); // 먼저 Http 요청 정보에서 로그인 세션이 있는지 찾아봄
        if (loginSessionCookie == null) { // 없으면 null 로 리턴
            return null;
        }
        return sessionStore.get(loginSessionCookie.getValue()); // 쿠키 찾으면 해당 로그인 세션을 가지고 있는 객체 반환
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName) { // 쿠키 가져오기
        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    /**
     * 세션 만료
     */

    public void expire(HttpServletRequest request) {
        Cookie loginSessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (loginSessionCookie != null) {
            sessionStore.remove(loginSessionCookie.getValue());
        }
    }



}
