package team1.issue_tracker.comment.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentListResponse {
    private long id;
    private String authorId;
    private String authorName;
    private String content;
    private List<String> fileUrls;
    private int likedCount;
    private LocalDateTime lastModifiedAt;
}
