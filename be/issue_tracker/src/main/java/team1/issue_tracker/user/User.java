package team1.issue_tracker.user;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERS")
@Getter
@Builder
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String password;
    private String name;
    private String profileImg;
    private UserType loginType;
    private LocalDateTime createdAt;
}