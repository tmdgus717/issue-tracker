package team1.issuetracker.domain.user.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticator implements Authenticator{

    private final JwtUtil jwtUtil;
    private final String AUTHORIZE_HEADER = "Authorization";
    private final String BEARER = "Bearer";

    @Autowired
    public JwtAuthenticator(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String authenticate(HttpServletRequest request) throws AuthenticationException {
        String jwtToken = parseBearerAuthorizeHeader(request.getHeader(AUTHORIZE_HEADER));
        String userId = jwtUtil.validateToken(jwtToken);
        if(userId == null) throw new AuthenticationException("유효하지 않은 JWT 토큰");

        return userId;
    }

    private String parseBearerAuthorizeHeader(String authorizeValue) throws AuthenticationException {
        try {
            return authorizeValue.substring(BEARER.length() + 1); // "Bearer" + "\s"
        }catch (NullPointerException noAuthorizeHeader){
            throw new AuthenticationException("인증 정보 미포함");
        }
    }
}
