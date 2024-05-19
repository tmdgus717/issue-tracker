package team1.issue_tracker.user.dto;

import team1.issue_tracker.user.User;

import java.time.LocalDateTime;

public record UserInfoResponse(String id, String nickname, String profileImage, LocalDateTime joinedAt) {

    public static UserInfoResponse of(User user ){
        return new UserInfoResponse(user.getId(), user.getName(), user.getProfileImg(), user.getCreatedAt());
    }
}
