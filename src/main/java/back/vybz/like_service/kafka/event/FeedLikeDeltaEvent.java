package back.vybz.like_service.kafka.event;

import back.vybz.like_service.like.domain.mongodb.FeedType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedLikeDeltaEvent  {

    private String feedId;
    private FeedType feedType;
    private int delta;

    @Builder
    public FeedLikeDeltaEvent(String feedId,
                                FeedType feedType,
                              int delta) {
        this.feedId = feedId;
        this.feedType = feedType;
        this.delta = delta;
    }
}
