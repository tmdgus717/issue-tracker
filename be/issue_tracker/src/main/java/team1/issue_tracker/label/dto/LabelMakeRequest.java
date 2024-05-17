package team1.issue_tracker.label.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LabelMakeRequest {
    private String name;
    private String description;
    private String color;
}
