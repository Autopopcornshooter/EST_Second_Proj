package EST.Baemin.Manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String username;
    private String password;
    private String nickname;
    private String storeName;
    private String confirmPassword;

}
