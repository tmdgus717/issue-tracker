package team1.issue_tracker.comment.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team1.issue_tracker.comment.Comment;
import team1.issue_tracker.comment.CommentFile;

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

    public static CommentListResponse of(Comment comment , String authorName){
        return new CommentListResponse(
                comment.getId(), comment.getUserId(),
                authorName,
                comment.getContent(),
                comment.getFiles().stream().map(CommentFile::getFileUrl).toList(),
                comment.getLikeCount(),
                comment.getLastModifiedAt()
        );
    }
}
