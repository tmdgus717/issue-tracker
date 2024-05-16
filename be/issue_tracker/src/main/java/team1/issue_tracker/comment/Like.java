package team1.issue_tracker.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USER_LIKES_COMMENT")
@AllArgsConstructor
@Builder
@Getter
public class Like {
    @Id
    private Long id;
    private String userId;
    private Long commentId;

    public static Like of(String userId , Long commentId){
        return Like.builder()
                .userId(userId)
                .commentId(commentId)
                .build();
    }
}
