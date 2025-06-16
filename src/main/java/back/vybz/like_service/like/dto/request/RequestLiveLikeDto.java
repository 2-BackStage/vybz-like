package back.vybz.like_service.like.dto.request;

import back.vybz.like_service.like.vo.request.RequestLiveLikeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class RequestLiveLikeDto {

    private String likerUuid;
    private String streamKey;

    @Builder
    public RequestLiveLikeDto(String likerUuid,
                              String streamKey) {
        this.likerUuid = likerUuid;
        this.streamKey = streamKey;
    }

    public static RequestLiveLikeDto from(RequestLiveLikeVo requestLiveLikeVo, String likerUuid) {
        return RequestLiveLikeDto.builder()
                .likerUuid(likerUuid)
                .streamKey(requestLiveLikeVo.getStreamKey())
                .build();
    }

}
