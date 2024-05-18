package team1.issuetracker.Issue.ref;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ISSUE_HAS_ASSIGNEE")
@Getter
@Builder
@AllArgsConstructor
public class AssigneeRef {
    @Column("ASSIGNEE_ID")
    private String userId;
}