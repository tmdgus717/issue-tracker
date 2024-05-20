package team1.issuetracker.domain.label;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team1.issuetracker.domain.label.dto.LabelListResponse;

import java.util.List;
import team1.issuetracker.domain.label.dto.LabelMakeRequest;

@RequestMapping("/label")
@RestController
public class LabelController {

    private final LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @GetMapping("/list")
    public List<LabelListResponse> labelList(){
        return labelService.getList();
    }

    @PostMapping
    public void createLabel(@RequestBody LabelMakeRequest labelMakeRequest){
        labelService.saveLabel(labelMakeRequest);
    }

    @PatchMapping("/{id}")
    public Label updateLabel(@PathVariable long id, @RequestBody LabelMakeRequest labelMakeRequest){
        return labelService.updateLabel(id, labelMakeRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteLabel(@PathVariable long id) {
        labelService.deleteLabel(id);
    }
}
