package team1.issue_tracker.label.dto;

import team1.issue_tracker.label.Label;
public record LabelListResponse(Long id, String name, String description, String color) {

    public static LabelListResponse of(Label label) {
        return new LabelListResponse(
                label.getId(),
                label.getName(),
                label.getColor(),
                label.getDescription());
    }
}
