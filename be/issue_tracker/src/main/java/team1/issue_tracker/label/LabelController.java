package team1.issue_tracker.label;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team1.issue_tracker.label.dto.LabelListResponse;

import java.util.List;

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
    public void makeLabel(){

    }

    @PatchMapping("/{id}")
    public Label modifyLabel(@PathVariable long id){
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteLabel(@PathVariable long id){

    }
}
