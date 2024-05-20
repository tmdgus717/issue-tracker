package team1.issuetracker.domain.comment;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import team1.issuetracker.domain.comment.dto.CommentPostRequest;

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
    public Comment modifyComment(@PathVariable long id, @RequestBody CommentPostRequest commentInfo, HttpServletRequest request) throws NoSuchFieldError{
        // 유저 ID와 작성자가 일치하지 않으면 예외
        if(!commentService.canModify(id, "test1")) throw new IllegalArgumentException("다른 사람의 댓글을 수정할 수 없습니다!");

        return commentService.modifyComment(id, "test1", commentInfo);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable long id) throws NoSuchElementException {
        commentService.deleteComment(id);
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
