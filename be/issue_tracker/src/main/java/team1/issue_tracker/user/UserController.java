package team1.issue_tracker.user;

import org.springframework.web.bind.annotation.*;
import team1.issue_tracker.user.dto.CheckDuplicateRequest;
import team1.issue_tracker.user.dto.RegisterInfo;
import team1.issue_tracker.user.dto.UserInfoResponse;

import java.util.NoSuchElementException;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void register(@RequestBody RegisterInfo registerInfo) throws IllegalArgumentException{
        System.out.println(registerInfo);
        userService.createUser(registerInfo);
    }

    @GetMapping("/duplicate")
    public boolean isDuplicate(@RequestBody CheckDuplicateRequest request){
        if(request.id() != null) return userService.isDuplicateId(request.id());
        if(request.nickname() != null) return userService.isDuplicateNickName(request.nickname());

        else throw new IllegalArgumentException("중복 확인할 ID나 닉네임을 입력해 주세요");
    }

    @PostMapping("/login")
    public Object login(@RequestBody Object loginInfo){

        return null;
    }

    @PostMapping("/logout")
    public void logout(){

    }

    @GetMapping("/{id}")
    public UserInfoResponse userInfo(@PathVariable String id) throws NoSuchElementException {
      return userService.getUserInfo(id);
    }
}
