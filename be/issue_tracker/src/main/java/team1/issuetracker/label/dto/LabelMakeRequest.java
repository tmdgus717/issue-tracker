package team1.issuetracker.label.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LabelMakeRequest {
    private String name;
    private String description;
    private String color;
}
