package team1.issue_tracker.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USER_LIKES_COMMENT")
@AllArgsConstructor
@Getter
public class Like {
    @Id
    private Long id;
    private Long userId;
    private Long commentId;
}
