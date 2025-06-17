package back.vybz.like_service.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LiveLikeDeltaEvent {

    private String streamKey;

    @Builder
    public LiveLikeDeltaEvent(String streamKey) {
        this.streamKey = streamKey;
    }
}
