package back.vybz.like_service.like.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestLiveLikeVo {

    private String streamKey;

    @Builder
    public RequestLiveLikeVo(String streamKey) {
        this.streamKey = streamKey;
    }
}
