package team1.issue_tracker.user;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team1.issue_tracker.util.repository.WithInsert;

@Repository
public interface UserRepository extends CrudRepository<User, String>, WithInsert<User> {
    boolean existsByName(String name);
}