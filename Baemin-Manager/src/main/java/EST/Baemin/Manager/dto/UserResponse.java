package EST.Baemin.Manager.dto;

import EST.Baemin.Manager.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {
    private final Long id;
    private final String nickname;

    private final String loginId;
    private final String passWord;
    private final LocalDateTime createdAt;
    private final String storeName;


    public UserResponse from(User user){
        return UserResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .loginId(user.getLoginId())
                .passWord(user.getPassword())
                .createdAt(user.getCreatedAt())
                .storeName(user.getStoreName())
                .build();
    }

}
