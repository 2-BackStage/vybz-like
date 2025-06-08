package back.vybz.like_service.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentLikeCountEvent {

    private String commentId;
    private int delta;

    @Builder
    public CommentLikeCountEvent(String commentId,
                                 int delta) {
        this.commentId = commentId;
        this.delta = delta;
    }
}
