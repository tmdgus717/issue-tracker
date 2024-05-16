package team1.issue_tracker.comment;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import team1.issue_tracker.comment.dto.CommentListResponse;
import team1.issue_tracker.comment.dto.CommentPostRequest;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/issue/{issueId}/comment/list")
    public List<CommentListResponse> commentsAtIssue(@PathVariable long issueId) {
        return commentService.getCommentsAtIssue(issueId);
    }

    @PostMapping("/issue/{issueId}/comment")
    public void writeComment(@PathVariable long issueId, @RequestBody CommentPostRequest commentInfo, HttpServletRequest request) {
        commentService.addComment(issueId, "test1", commentInfo); // 로그인 기능 구현 전 임시로 'test1' 유저 사용
    }

    @PatchMapping("/comment/{id}")
    public Comment modifyComment(@PathVariable long id) {
        return null;
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable long id) {

    }

    @PostMapping("/comment/{id}/like")
    public int likeComment(@PathVariable long id, HttpServletRequest request) {
        return commentService.likeComment("test1", id); // 로그인 기능 구현 전 임시로 'test1' 유저 사용
    }

    @PostMapping("/comment/{id}/unlike")
    public int unLikeComment(@PathVariable long id, HttpServletRequest request) {
        return commentService.unlikeComment("test1", id);
    }
}
