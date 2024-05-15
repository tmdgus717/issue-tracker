package team1.issue_tracker.label;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/label")
@RestController
public class LabelController {

    @GetMapping("/list")
    public List<Label> labelList(){
        return null;
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
