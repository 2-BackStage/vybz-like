package back.vybz.like_service.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedLikeCountEvent  {

    private String feedId;
    private int delta;

    @Builder
    public FeedLikeCountEvent(String feedId,
                              int delta) {
        this.feedId = feedId;
        this.delta = delta;
    }
}
