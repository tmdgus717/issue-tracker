package team1.issuetracker.domain.user.auth.exception;

public class AuthenticateException extends RuntimeException {

    public AuthenticateException(String message){
        super(message);
    }
}
