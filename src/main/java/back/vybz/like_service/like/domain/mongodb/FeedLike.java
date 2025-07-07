package back.vybz.like_service.like.domain.mongodb;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.CompoundIndex;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Document("feed_like")
@CompoundIndex(name = "unique_feed_like", def = "{'feedId': 1, 'likerUuid': 1}", unique = true)
public class FeedLike {

    @Id
    private String id;

    // 피드 id
    private String feedId;

    // 피드 타입
    private FeedType feedType;

    //좋아요 누른사람 UUID
    private String likerUuid;

    // 좋아요 누른 사람 타입
    private WriterType likerType;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Builder
    public FeedLike(String feedId,
                    FeedType feedType,
                    String likerUuid,
                    WriterType likerType) {
        this.feedId = feedId;
        this.feedType = feedType;
        this.likerUuid = likerUuid;
        this.likerType = likerType;
    }


}
