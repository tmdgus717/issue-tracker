package team1.issue_tracker.Issue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import team1.issue_tracker.comment.Comment;
import team1.issue_tracker.comment.CommentRepository;
import team1.issue_tracker.user.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
public class IssueRelationshipTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    IssueRepository issueRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    @DisplayName("작성자 ID와 제목만 전달하여 새 이슈를 저장할 수 있다.")
    @Test
    @Transactional
    void saveEmptyIssue() {
        // given
        Issue issue = Issue.makeOnlyIssue("test", "제목");

        // when
        issueRepository.save(issue);
        List<Issue> byId = (List<Issue>) issueRepository.findAll();

        //then
        assertThat(byId).hasSize(1);
        Issue found = byId.get(0);
        assertThat(found.getTitle()).isEqualTo("제목");
        assertThat(found.getUserId()).isEqualTo("test");
    }

    @DisplayName("이슈의 Comments에 Comments 추가한 후, 이슈를 저장하면 Comment Table에도 저장된다.")
    @Test
    @Transactional
    void saveCommentThroughIssue() {
        // given
        Comment comment = Comment.makeOnlyComment(1L, "test", "댓글내용");
        Issue issue = Issue.makeOnlyIssue("test", "제목");

        // when
        issueRepository.save(issue); // Insert
        issue.getComments().add(comment);
        issueRepository.save(issue); // Update
        Optional<Issue> byId = issueRepository.findById(1L);

        //then
        List<Comment> comments = byId.get().getComments();
        List<Comment> findComments = (List<Comment>) commentRepository.findAll();

        assertThat(comments.get(0).equals(findComments.get(0))).isTrue();
    }
}
