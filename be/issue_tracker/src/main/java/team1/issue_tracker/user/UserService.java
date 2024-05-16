package team1.issue_tracker.user;

import org.springframework.stereotype.Service;
import team1.issue_tracker.user.dto.RegisterInfo;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getNameById(String userId){
        Optional<User> byId = userRepository.findById(userId);
        return byId.get().getName();
    }

    public boolean isDuplicateId(String id) {
        return userRepository.existsById(id);
    }

    public boolean isDuplicateNickName(String nickname) {
        return userRepository.existsByName(nickname);
    }

    public void createUser(RegisterInfo registerInfo) {
        if(isDuplicateId(registerInfo.id())) throw new IllegalArgumentException("이미 등록된 ID 입니다");
        if(isDuplicateId(registerInfo.nickname())) throw new IllegalArgumentException("이미 등록된 닉네임 입니다");

        User newUser = User.normalUSerOf(registerInfo);
        userRepository.insert(newUser);
    }
}
