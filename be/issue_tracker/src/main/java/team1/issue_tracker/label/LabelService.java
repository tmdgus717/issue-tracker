package team1.issue_tracker.label;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.Issue.Issue;
import team1.issue_tracker.label.dto.LabelListResponse;

import java.util.List;
import java.util.Set;

import team1.issue_tracker.label.dto.LabelMakeRequest;


@Service
public class LabelService {

    private final LabelRepository labelRepository;

    @Autowired
    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public void saveLabel(LabelMakeRequest labelMakeRequest) {
        Label label = Label.of(labelMakeRequest);
        labelRepository.save(label);
    }

    public Label updateLabel(long id, LabelMakeRequest labelMakeRequest) {
        Label savedLabel = getLabel(id);

        return savedLabel;
    }

    public void deleteLabel(Long id) throws NoSuchElementException {
        labelRepository.delete(getLabel(id));
    }

    private Label getLabel(Long id) throws NoSuchElementException {
        Optional<Label> optionalLabel = labelRepository.findById(id);
        if (optionalLabel.isEmpty()) throw new NoSuchElementException(id + "라벨이 존재하지 않습니다!");

        return optionalLabel.get();
    }

    public List<LabelListResponse> getList(){
        List<Label> labelList = (List<Label>) labelRepository.findAll();
        return labelList.stream().map(LabelListResponse::of).toList();
    }

    public List<LabelListResponse> getLabelsAyIssue(Issue issue) {
        Set<IssueLabel> issueLabels = issue.getIssueHasLabel();
        List<Long> labelIds = issueLabels.stream().map(IssueLabel::getLabelId).toList();

        return labelRepository.findByIdIn(labelIds).stream().map(LabelListResponse::of).toList();
    }
}
