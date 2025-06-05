package back.vybz.like_service.like.infrastructure;


import back.vybz.like_service.like.domain.mongodb.CommentLike;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommentLikeRepository extends MongoRepository<CommentLike, String> {
    Optional<CommentLike> findByCommentIdAndLikerUuid(String commentId, String likerUuid);
}

