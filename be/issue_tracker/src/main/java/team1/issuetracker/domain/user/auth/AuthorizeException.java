package team1.issuetracker.domain.user.auth;

public class AuthorizeException extends RuntimeException {

    public AuthorizeException(String message){
        super(message);
    }
}
