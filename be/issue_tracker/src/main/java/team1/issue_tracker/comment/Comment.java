package team1.issue_tracker.comment;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("COMMENT")
@Getter
@AllArgsConstructor
public class Comment {
    @Id
    @Generated
    private Long id;
    private Long issueId;
    private String userId;
    private String content;
    @MappedCollection(idColumn = "comment_id", keyColumn = "id")
    private List<CommentFile> files;

    private LocalDateTime lastModifiedAt;
    private LocalDateTime createdAt;

    public String getContent() {
        return content;
    }
}