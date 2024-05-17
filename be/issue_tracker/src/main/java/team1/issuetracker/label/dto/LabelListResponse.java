package team1.issuetracker.label.dto;

import team1.issuetracker.label.Label;
public record LabelListResponse(Long id, String name, String description, String color) {

    public static LabelListResponse of(Label label) {
        return new LabelListResponse(
                label.getId(),
                label.getName(),
                label.getDescription(),
                label.getColor());
    }
}
