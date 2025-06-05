package back.vybz.like_service.like.infrastructure;


import back.vybz.like_service.like.domain.mongodb.FeedLike;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeedLikeRepository extends MongoRepository<FeedLike, String> {
    Optional<FeedLike> findByFeedIdAndLikerUuid(String feedId, String likerUuid);
}
