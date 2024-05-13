package team1.issue_tracker.Issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team1.issue_tracker.Issue.Issue;
import team1.issue_tracker.Issue.IssueStatus;
import team1.issue_tracker.comment.dto.CommentListResponse;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.milestone.Milestone;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class IssueShowResponse {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime lastModifiedAt;
    private IssueStatus status;
    private List<String> assignees;
    private List<Label> labels;
    private Milestone milestone;
    private List<CommentListResponse> comments;

    public static IssueShowResponse of(Issue issue,
                                       String authorName,
                                       List<String> assignees,
                                       List<Label> labels,
                                       Milestone milestone,
                                       List<CommentListResponse> comments) {

        return new IssueShowResponse(
                issue.getId(),
                issue.getTitle(),
                authorName,
                issue.getLastModifiedAt(),
                issue.getStatus(),
                assignees,
                labels,
                milestone,
                comments
        );
    }
}