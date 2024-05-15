package team1.issue_tracker.label;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team1.issue_tracker.label.dto.LabelListResponse;

import java.util.List;

@Service
public class LabelService {

    private final LabelRepository labelRepository;

    @Autowired
    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public List<LabelListResponse> getList(){
        List<Label> labelList = (List<Label>) labelRepository.findAll();
        return labelList.stream().map(LabelListResponse::of).toList();
    }
}
