package team1.issue_tracker.user;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @PostMapping
    public void register(){

    }

    @GetMapping("/duplicate")
    public boolean isDuplicateId(@RequestBody String id){
        return false;
    }

    @GetMapping("/duplicate")
    public boolean isDuplicateNickName(@RequestBody String nickName){
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
