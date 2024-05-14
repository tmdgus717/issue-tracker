package team1.issue_tracker.Issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.Issue.dto.IssueListResponse;
import team1.issue_tracker.Issue.dto.IssueShowResponse;
import team1.issue_tracker.comment.Comment;
import team1.issue_tracker.comment.CommentRepository;
import team1.issue_tracker.comment.dto.CommentListResponse;
import team1.issue_tracker.label.IssueLabel;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.label.LabelRepository;
import team1.issue_tracker.milestone.Milestone;
import team1.issue_tracker.milestone.MilestoneRepository;
import team1.issue_tracker.user.IssueAssignee;
import team1.issue_tracker.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final LabelRepository labelRepository;
    private final MilestoneRepository milestoneRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    @Autowired
    public IssueService(IssueRepository issueRepository, LabelRepository labelRepository,
                        MilestoneRepository milestoneRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.issueRepository = issueRepository;
        this.labelRepository = labelRepository;
        this.milestoneRepository = milestoneRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public List<IssueListResponse> getList() {
        List<Issue> issueList = issueRepository.findAllByStatus(IssueStatus.OPEN);

        return issueList.stream().map(issue -> new IssueListResponse(
                issue.getId(),
                issue.getTitle(),
                commentRepository.findFirstByIssueId(issue.getId()).getContent(),
                labelsAtIssue(issue),
                milestoneAtIssue(issue))).toList();
    }

    public IssueShowResponse showIssue(Long id) {
        Optional<Issue> optionalIssue = issueRepository.findById(id);
        if (optionalIssue.isEmpty()) return null;

        Issue issue = optionalIssue.get();
        String name = userRepository.findNameById(issue.getUserId());

        return IssueShowResponse.of(issue, name, assigneesAtIssue(issue), labelsAtIssue(issue), milestoneAtIssue(issue), commentsAtIssue(issue));
    }


    public void closeIssue(Long id) {
        Optional<Issue> optionalIssue = issueRepository.findById(id);
        if (optionalIssue.isEmpty()) {
            return;
        }

        Issue issue = optionalIssue.get();
        issue.setStatus(IssueStatus.CLOSE);
        issueRepository.save(issue);
    }

    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }

    private List<CommentListResponse> commentsAtIssue(Issue issue) {
        List<Comment> comments = commentRepository.findAllByIssueId(issue.getId());

        return comments.stream()
                .map(comment -> CommentListResponse.of(comment, userRepository.findNameById(comment.getUserId()))
                ).toList();
    }

    private List<Label> labelsAtIssue(Issue issue) {
        List<IssueLabel> issueLabels = issue.getIssueHasLabel();
        List<Long> labelIds = issueLabels.stream().map(IssueLabel::getLabelId).toList();

        return (List<Label>) labelRepository.findAllById(labelIds);
    }

    private Milestone milestoneAtIssue(Issue issue) {
        Long milestoneId = issue.getMilestoneId();
        if (milestoneId == null) return null;

        return milestoneRepository.findById(milestoneId).get();
    }

    private List<String> assigneesAtIssue(Issue issue) {
        List<IssueAssignee> issueAssignees = issue.getIssueAssignees();
        return issueAssignees.stream()
                .map(IssueAssignee::getAssigneeId)
                .map(userRepository::findNameById).toList();
    }
}