package team1.issuetracker.domain.label;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import team1.issuetracker.domain.label.dto.LabelMakeRequest;

import java.time.LocalDateTime;

@Table("LABEL")
@Getter
@Builder
@AllArgsConstructor
public class Label {
    @Id
    @Generated
    private Long id;
    private String name;
    private String description;
    private String color;
    private String userId;
    @CreatedDate
    private LocalDateTime createdAt;

    public static Label of(LabelMakeRequest labelMakeRequest, String userId) {
        return Label.builder()
                .name(labelMakeRequest.name())
                .description(labelMakeRequest.description())
                .color(labelMakeRequest.color())
                .userId(userId)
                .build();
    }
}
