package back.vybz.like_service.like.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class ResponseFeedLikeVo {

    private String feedId;
    private String likerUuid;
    private boolean liked;
    private Instant createdAt;


    @Builder
    public ResponseFeedLikeVo(String feedId,
                              String likerUuid,
                              boolean liked,
                              Instant createdAt) {
        this.feedId = feedId;
        this.likerUuid = likerUuid;
        this.liked = liked;
        this.createdAt = createdAt;
    }
}
