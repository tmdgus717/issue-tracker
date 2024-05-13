package team1.issue_tracker.user;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query("SELECT NAME FROM USERS WHERE ID = :id")
    String findNameById(@Param("id") String id);
}