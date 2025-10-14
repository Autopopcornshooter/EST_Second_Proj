package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.repository.UserRepository;
import EST.Baemin.Manager.service.LikeService;
import EST.Baemin.Manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/{id}/like")
    public ResponseEntity<Map<String, Object>> toggleLike(
            @PathVariable Long id
//            @AuthenticationPrincipal User user
    ) {
        // 테스트용: DB에서 id=1인 유저 가져오기
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("테스트 유저가 존재하지 않습니다."));

        boolean liked = likeService.toggleLike(user, id);
        int likeCount = likeService.getLikeCount(id);

        Map<String, Object> response = new HashMap<>();
        response.put("liked", liked);
        response.put("likeCount", likeCount);

        return ResponseEntity.ok(response);
    }
}
