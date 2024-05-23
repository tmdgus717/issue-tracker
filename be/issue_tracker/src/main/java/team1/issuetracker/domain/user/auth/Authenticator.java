package team1.issuetracker.domain.user.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.websocket.AuthenticationException;

public interface Authenticator {
    String authenticate(HttpServletRequest request) throws AuthenticationException;
}
