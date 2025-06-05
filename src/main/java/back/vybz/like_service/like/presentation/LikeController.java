package back.vybz.like_service.like.presentation;


import back.vybz.like_service.like.application.service.CommentLikeService;
import back.vybz.like_service.like.application.service.FeedLikeService;
import back.vybz.like_service.like.dto.request.RequestCommentLikeDto;
import back.vybz.like_service.like.dto.request.RequestFeedLikeDto;
import back.vybz.like_service.like.dto.response.ResponseCommentLikeDto;
import back.vybz.like_service.like.dto.response.ResponseFeedLikeDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class LikeController {

    private final FeedLikeService feedLikeService;
    private final CommentLikeService commentLikeService;

    @Operation(
            summary = "피드 좋아요 토글 API",
            description = "피드에 좋아요를 토글합니다. 좋아요가 없으면 추가하고, 있으면 제거합니다.",
            tags = {"LIKE-SERVICE"}
    )
    @PostMapping("/feed")
    public ResponseEntity<ResponseFeedLikeDto> toggleFeedLike(@RequestBody RequestFeedLikeDto requestFeedLikeDto) {
        ResponseFeedLikeDto responseFeedLikeDto = feedLikeService.toggleFeedLike(requestFeedLikeDto);
        return ResponseEntity.ok(responseFeedLikeDto);
    }

    @Operation(
            summary = "댓글 좋아요 토글 API",
            description = "댓글에 좋아요를 토글합니다. 좋아요가 없으면 추가하고, 있으면 제거합니다.",
            tags = {"LIKE-SERVICE"}
    )
    @PostMapping("/comment")
    public ResponseEntity<ResponseCommentLikeDto> toggleCommentLike(@RequestBody RequestCommentLikeDto requestCommentLikeDto) {
        ResponseCommentLikeDto responseCommentLikeDto = commentLikeService.toggleCommentLike(requestCommentLikeDto);
        return ResponseEntity.ok(responseCommentLikeDto);
    }

}
