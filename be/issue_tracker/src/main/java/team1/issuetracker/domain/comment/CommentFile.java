package team1.issuetracker.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("COMMENT_FILE")
@Getter
@ToString
@AllArgsConstructor
public class CommentFile {
    @Id
    private Long id;
    private Long commentId;
    private String fileUrl;
}
