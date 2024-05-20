package team1.issuetracker.domain.label.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record LabelMakeRequest(String name, String description, String color) {
}
