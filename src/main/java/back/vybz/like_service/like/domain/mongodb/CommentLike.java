package back.vybz.like_service.like.domain.mongodb;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.Instant;

@Getter
@NoArgsConstructor
@Document(collection = "comment_likes")
public class CommentLike {

    @Id
    private String id;

    // 댓글 id
    private String commentId;

    //좋아요 누른사람 UUID
    private String likerUuid;

    // 좋아요 누른 사람 타입
    private WriterType likerType;

    //댓글작성자 uuid
    private String writerUuid;

    //작성자 타입
    private WriterType writerType;

    //대댓글 id
    private String parentCommentId;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Builder
    public CommentLike(String commentId,
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
