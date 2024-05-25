package team1.issuetracker.domain.Issue.dto;

import team1.issuetracker.domain.Issue.Issue;
import team1.issuetracker.domain.Issue.IssueStatus;
import team1.issuetracker.domain.Issue.ref.AssigneeRef;
import team1.issuetracker.domain.Issue.ref.LabelRef;

import java.util.List;
import java.util.stream.Collectors;

public record IssueUpdateRequest(String title , List<String> assigneeIds,  List<Long> labelIds, Long milestoneId) {

    public Issue toIssue(Issue origin){
        return Issue.builder()
                .id(origin.getId())
                .title(title)
                .userId(origin.getUserId())
                .issueAssignees(assigneeIds.stream().map(AssigneeRef::new).collect(Collectors.toSet()))
                .issueHasLabel(labelIds.stream().map(LabelRef::new).collect(Collectors.toSet()))
                .milestoneId(milestoneId)
                .status(IssueStatus.OPEN)
                .createdAt(origin.getCreatedAt())
                .build();
    }
}
