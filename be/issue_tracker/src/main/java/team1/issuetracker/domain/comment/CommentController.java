package team1.issuetracker.domain.comment;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import team1.issuetracker.domain.comment.dto.CommentPostRequest;
import team1.issuetracker.domain.user.auth.AuthorizeException;

import java.util.NoSuchElementException;


@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public void writeComment(@RequestBody CommentPostRequest commentInfo, HttpServletRequest request) {
        commentService.addComment(commentInfo.issueId(), "test1", commentInfo); // 로그인 기능 구현 전 임시로 'test1' 유저 사용
    }

    @PatchMapping("/comment/{id}")
    public Comment modifyComment(@PathVariable long id, @RequestBody CommentPostRequest commentInfo,
                                 HttpServletRequest request) throws NoSuchElementException, AuthorizeException {
        String userId = "test1";
        return commentService.modifyComment(id, "test1", commentInfo);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable long id, HttpServletRequest request) throws NoSuchElementException, AuthorizeException {
        String userId = "test1";
        commentService.deleteComment(id, userId);
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
