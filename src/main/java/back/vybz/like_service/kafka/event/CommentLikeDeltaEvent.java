package back.vybz.like_service.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentLikeDeltaEvent {

    private String commentId;
    private int delta;

    @Builder
    public CommentLikeDeltaEvent(String commentId,
                                 int delta) {
        this.commentId = commentId;
        this.delta = delta;
    }
}
