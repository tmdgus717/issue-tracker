package team1.issuetracker.domain.Issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team1.issuetracker.domain.Issue.Issue;
import team1.issuetracker.domain.Issue.IssueStatus;
import team1.issuetracker.domain.Issue.ref.AssigneeRef;
import team1.issuetracker.domain.Issue.ref.LabelRef;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class IssueMakeRequest {
    private String title;
    private String comment;
    private List<String> assigneeIds;
    private List<Long> labelIds;
    private Long milestoneId;

    public Issue toIssue(String userId) {
        Set<AssigneeRef> issueAssignees = assigneeIds.stream()
                .map(assigneeId -> AssigneeRef.builder().userId(assigneeId).build())
                .collect(Collectors.toSet());

        Set<LabelRef> issueHasLabel = labelIds.stream().map(labelId -> LabelRef.builder().labelId(labelId).build())
                .collect(Collectors.toSet());

        return Issue.builder()
                .userId(userId)
                .title(title)
                .issueHasLabel(issueHasLabel)
                .issueAssignees(issueAssignees)
                .milestoneId(milestoneId)
                .status(IssueStatus.OPEN)
                .build();
    }
}