package team1.issue_tracker.user;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import team1.issue_tracker.user.dto.RegisterInfo;

@Table("USERS")
@ToString
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
    @CreatedDate
    private LocalDateTime createdAt;

    public static User normalUSerOf(RegisterInfo registerInfo){
        return User.builder()
                .id(registerInfo.id())
                .name(registerInfo.nickname())
                .password(registerInfo.password())
                .loginType(UserType.NORMAL)
                .build();
    }
}