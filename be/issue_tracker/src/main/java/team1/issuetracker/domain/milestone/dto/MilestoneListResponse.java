package team1.issuetracker.domain.milestone.dto;

import team1.issuetracker.domain.milestone.Milestone;

import java.time.LocalDateTime;

public record MilestoneListResponse(long id, String name, String description, LocalDateTime deadline, int openCounts,
                                    int closedCounts, int completion) {
    public static MilestoneListResponse of(Milestone milestone) {
        return new MilestoneListResponse(milestone.getId(), milestone.getName(),
                milestone.getDescription(), milestone.getDeadline(), milestone.getOpenIssueCount(),
                milestone.getClosedIssueCount(),
                milestone.getCompletionPercentage());
    }
}