package back.vybz.like_service.like.vo.request;

import back.vybz.like_service.like.domain.mongodb.WriterType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCommentLikeVo {

    private String commentId;
    private String likerUuid;
    private WriterType likerType;
    private String writerUuid;
    private WriterType writerType;
    private String parentCommentId;

    @Builder
    public RequestCommentLikeVo(String commentId,
                                String likerUuid,
                                WriterType likerType,
                                String writerUuid,
                                WriterType writerType,
                                String parentCommentId) {
        this.commentId = commentId;
        this.likerUuid = likerUuid;
        this.likerType = likerType;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.parentCommentId = parentCommentId;
    }
}
