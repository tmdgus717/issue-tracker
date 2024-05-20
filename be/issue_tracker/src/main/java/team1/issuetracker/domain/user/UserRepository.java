package team1.issuetracker.domain.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team1.issuetracker.util.repository.WithInsert;

@Repository
public interface UserRepository extends CrudRepository<User, String>, WithInsert<User> {
    boolean existsByName(String name);
}