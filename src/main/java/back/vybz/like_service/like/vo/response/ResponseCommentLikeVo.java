package back.vybz.like_service.like.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class ResponseCommentLikeVo {
    private String commentId;
    private String likerUuid;
    private boolean liked;
    private Instant createdAt;

    @Builder
    public ResponseCommentLikeVo(String commentId,
                                 String likerUuid,
                                 boolean liked,
                                 Instant createdAt) {
        this.commentId = commentId;
        this.likerUuid = likerUuid;
        this.liked = liked;
        this.createdAt = createdAt;
    }
}
