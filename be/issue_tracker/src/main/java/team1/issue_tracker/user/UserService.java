package team1.issue_tracker.user;

import org.springframework.stereotype.Service;

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
}
