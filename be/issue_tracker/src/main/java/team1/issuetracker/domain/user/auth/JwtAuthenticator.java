package team1.issuetracker.domain.user.auth;

import jakarta.servlet.http.HttpServletRequest;
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
    public String authenticate(HttpServletRequest request) {
        String jwtToken = parseBearerAuthorizeHeader(request.getHeader(AUTHORIZE_HEADER));
        String userId = jwtUtil.validateToken(jwtToken);
        if(userId == null) throw new IllegalArgumentException("유효하지 않은 JWT 토큰");

        return userId;
    }

    private String parseBearerAuthorizeHeader(String authorizeValue){
        return authorizeValue.substring(BEARER.length()+1); // "Bearer" + "\s"
    }
}
