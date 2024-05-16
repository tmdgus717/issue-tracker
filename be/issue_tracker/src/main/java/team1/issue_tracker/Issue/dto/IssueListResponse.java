package team1.issue_tracker.Issue.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team1.issue_tracker.Issue.Issue;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.label.dto.LabelListResponse;
import team1.issue_tracker.milestone.dto.MilestoneListResponse;
import team1.issue_tracker.milestone.dto.MilestoneShowResponse;

@Getter
@AllArgsConstructor
public class IssueListResponse {
    private long id;
    private String title;
    private String comment;
    private List<LabelListResponse> labels;
    private MilestoneShowResponse milestone;

    public static IssueListResponse of(Issue issue , String comment, List<LabelListResponse> labels, MilestoneShowResponse milestone){
        return new IssueListResponse(
                issue.getId(),
                issue.getTitle(),
                comment,
                labels,
                milestone
        );
    }
}