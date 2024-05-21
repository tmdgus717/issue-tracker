package team1.issuetracker.domain.user.auth;

public interface Authorizable<T , ID> {
    T authorize(ID id, String userId);
}
