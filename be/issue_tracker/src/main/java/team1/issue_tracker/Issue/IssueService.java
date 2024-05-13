package team1.issue_tracker.Issue;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.Issue.dto.IssueListResponse;
import team1.issue_tracker.Issue.dto.IssueShowResponse;
import team1.issue_tracker.comment.Comment;
import team1.issue_tracker.comment.CommentFile;
import team1.issue_tracker.comment.dto.CommentListResponse;
import team1.issue_tracker.label.IssueLabel;
import team1.issue_tracker.label.Label;
import team1.issue_tracker.label.LabelRepository;

import java.util.List;
import team1.issue_tracker.milestone.Milestone;
import team1.issue_tracker.milestone.MilestoneRepository;
import team1.issue_tracker.user.IssueAssignee;
import team1.issue_tracker.user.UserRepository;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final LabelRepository labelRepository;
    private final MilestoneRepository milestoneRepository;
    private final UserRepository userRepository;


    @Autowired
    public IssueService(IssueRepository issueRepository, LabelRepository labelRepository,
                        MilestoneRepository milestoneRepository, UserRepository userRepository) {
        this.issueRepository = issueRepository;
        this.labelRepository = labelRepository;
        this.milestoneRepository = milestoneRepository;
        this.userRepository = userRepository;
    }

    public List<IssueListResponse> getList() {
        List<Issue> issueList = (List<Issue>) issueRepository.findAll();

        return issueList.stream().map(issue -> new IssueListResponse(
                issue.getId(),
                issue.getTitle(),
                issue.getComments().get(0).getContent(),
                labelsAtIssue(issue),
                getMilestone(issue))).toList();
    }

    private List<Label> labelsAtIssue(Issue issue) {
        List<IssueLabel> issueLabels = issue.getIssueHasLabel();
        List<Long> labelIds = issueLabels.stream().map(IssueLabel::getLabelId).toList();

        return (List<Label>) labelRepository.findAllById(labelIds);
    }

    private List<CommentListResponse> commentsAtIssue(Issue issue) {
        List<Comment> comments = issue.getComments();

        return comments.stream().map(comment -> new CommentListResponse(comment.getId(), comment.getUserId(),
                userRepository.findNameById(comment.getUserId()), comment.getContent(),
                comment.getFiles().stream().map(CommentFile::getFileUrl).toList(),
                0,
                comment.getLastModifiedAt()
        )).toList();
    }

    private Milestone getMilestone(Issue issue) {
        Long milestoneId = issue.getMilestoneId();
        if (milestoneId == null) {
            return null;
        }

        return milestoneRepository.findById(milestoneId).get();
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

    public IssueShowResponse showIssue(Long id) {
        Optional<Issue> optionalIssue = issueRepository.findById(id);
        if (optionalIssue.isEmpty()) {
            return null;
        }

        Issue issue = optionalIssue.get();
        String userId = issue.getUserId();
        String name = userRepository.findNameById(userId);

        System.out.println("name = " + name);

        List<IssueAssignee> issueAssignees = issue.getIssueAssignees();
        List<String> assignees = issueAssignees.stream().map(IssueAssignee::getAssigneeId)
                .map(userRepository::findNameById).toList();

        return new IssueShowResponse(id, issue.getTitle(), name,
                issue.getLastModifiedAt(), issue.getStatus(), assignees, labelsAtIssue(issue),
                getMilestone(issue), commentsAtIssue(issue)
        );
    }
}