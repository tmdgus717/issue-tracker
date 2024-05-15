package team1.issue_tracker.comment;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import team1.issue_tracker.comment.dto.CommentListResponse;

import java.util.List;


@RestController
public class CommentController {

    @GetMapping("/issue/{issueId}/comment/list")
    public List<CommentListResponse> commentsAtIssue(@PathVariable long issueId){
        return null;
    }

    @PostMapping("/issue/{issueId}/comment")
    public void writeComment(@PathVariable long issueId){

    }

    @PatchMapping("/comment/{id}")
    public Comment modifyComment(@PathVariable long id){
        return null;
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable long id){

    }

    @PostMapping("/comment/{id}/like")
    public int likeComment(@PathVariable long id , HttpServletRequest request){
        return 0;
    }

    @PostMapping("/comment/{id}/unlike")
    public int unLikeComment(@PathVariable long id , HttpServletRequest request){
        return 0;
    }
}
