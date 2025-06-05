package back.vybz.like_service.like.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class ResponseCommentLikeDto {
    private String commentId;
    private String likerUuid;
    private boolean liked;
    private Instant createdAt;

    @Builder
    public ResponseCommentLikeDto(String commentId,
                                  String likerUuid,
                                  boolean liked,
                                  Instant createdAt) {
        this.commentId = commentId;
        this.likerUuid = likerUuid;
        this.liked = liked;
        this.createdAt = createdAt;
    }
}
