package team1.issuetracker.domain.label;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team1.issuetracker.domain.label.dto.LabelListResponse;

import java.util.List;
import team1.issuetracker.domain.label.dto.LabelMakeRequest;
import team1.issuetracker.domain.user.auth.Authenticator;

@RequestMapping("/label")
@RestController
public class LabelController {

    private final LabelService labelService;
    private final Authenticator authenticator;

    @Autowired
    public LabelController(LabelService labelService, Authenticator authenticator) {
        this.labelService = labelService;
        this.authenticator = authenticator;
    }

    @GetMapping("/list")
    public List<LabelListResponse> labelList(){
        return labelService.getList();
    }

    @PostMapping
    public LabelListResponse createLabel(@RequestBody LabelMakeRequest labelMakeRequest, HttpServletRequest httpServletRequest){
        String userId = authenticator.authenticate(httpServletRequest);
        return LabelListResponse.of(labelService.saveLabel(labelMakeRequest, userId));
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
