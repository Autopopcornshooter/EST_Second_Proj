package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.repository.UserRepository;
import EST.Baemin.Manager.service.LikeService;
import EST.Baemin.Manager.repository.UserRepository;
import EST.Baemin.Manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class LikeController {
    private final LikeService likeService;
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/{id}/like")
    public ResponseEntity<Map<String, Object>> toggleLike(@PathVariable Long id) {

        // 현재 로그인한 유저 가져오기
        User user = userService.authenticatedUser();

        boolean liked = likeService.toggleLike(user, id);
        int likeCount = likeService.getLikeCount(id);

        Map<String, Object> response = new HashMap<>();
        response.put("liked", liked);
        response.put("likeCount", likeCount);

        return ResponseEntity.ok(response);
    }
}
