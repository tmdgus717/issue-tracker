package team1.issuetracker.domain.user.auth.exception;

public class AuthorizeException extends RuntimeException {

    public AuthorizeException(String message){
        super(message);
    }
}
