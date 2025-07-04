package back.vybz.like_service.like.domain.mongodb;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Document(collection = "live_likes")
@CompoundIndex(name = "unique_liker_stream", def = "{'likerUuid' : 1, 'streamKey': 1}", unique = true)
public class LiveLike {

    @Id
    private String id;

    private String streamKey;

    @CreatedDate
    private Instant createdAt;

    @Builder
    public LiveLike(String streamKey) {
        this.streamKey = streamKey;
    }
}
