package team1.issuetracker.label;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ISSUE_HAS_LABEL")
@Getter
@Builder
@AllArgsConstructor
public class IssueLabel {
    @Id
    @Generated
    private Long id;
    private Long issueId;
    private Long labelId;

    public static IssueLabel of(Long issueId , Long labelId){
        return IssueLabel.builder()
                .issueId(issueId)
                .labelId(labelId)
                .build();
    }
}