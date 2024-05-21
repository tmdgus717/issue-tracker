package team1.issuetracker.domain.user.auth;

import jakarta.servlet.http.HttpServletRequest;

public interface Authenticator {
    String authenticate(HttpServletRequest request);
}
