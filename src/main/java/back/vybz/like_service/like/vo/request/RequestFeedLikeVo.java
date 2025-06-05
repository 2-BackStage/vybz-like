package back.vybz.like_service.like.vo.request;

import back.vybz.like_service.like.domain.mongodb.FeedType;
import back.vybz.like_service.like.domain.mongodb.WriterType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestFeedLikeVo {
    private String feedId;
    private FeedType feedType;
    private String likerUuid;
    private WriterType likerType;

    @Builder
    public RequestFeedLikeVo(String feedId,
                             FeedType feedType,
                             String likerUuid,
                             WriterType likerType) {
        this.feedId = feedId;
        this.feedType = feedType;
        this.likerUuid = likerUuid;
        this.likerType = likerType;
    }
}
