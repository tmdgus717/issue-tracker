package team1.issue_tracker.user;

import org.springframework.web.bind.annotation.*;
import team1.issue_tracker.user.dto.CheckDuplicateRequest;

@RequestMapping("/user")
@RestController
public class UserController {

    @PostMapping
    public void register(){

    }

    @GetMapping("/duplicate")
    public boolean isDuplicate(@RequestBody CheckDuplicateRequest request){
        return false;
    }

    @PostMapping("/login")
    public Object login(@RequestBody Object loginInfo){

        return null;
    }

    @PostMapping("/logout")
    public void logout(){

    }

    @GetMapping("/{id}")
    public User userInfo(@PathVariable String id){
        return null;
    }
}
