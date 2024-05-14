package team1.issue_tracker.Issue;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import team1.issue_tracker.comment.Comment;
import team1.issue_tracker.label.IssueLabel;
import team1.issue_tracker.user.IssueAssignee;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Table("ISSUE")
@Getter
@Builder
@ToString
@AllArgsConstructor
public class Issue {
    @Id
    private Long id;
    private String userId;
    private Long milestoneId;
    private String title;
    @MappedCollection(idColumn = "ISSUE_ID", keyColumn = "ID")
    private List<IssueLabel> issueHasLabel;
    @MappedCollection(idColumn = "ISSUE_ID", keyColumn = "ID")
    private List<IssueAssignee> issueAssignees;
    private IssueStatus status;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public static Issue makeOnlyIssue(String authorId, String title){
        return Issue.builder()
                .userId(authorId)
                .title(title)
                .status(IssueStatus.OPEN)
                .build();
    }
}