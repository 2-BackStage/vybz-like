package back.vybz.like_service.like.infrastructure;

import back.vybz.like_service.like.domain.mongodb.LiveLike;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LiveLikeRepository extends MongoRepository<LiveLike,String> {

    boolean existsByLikerUuidAndStreamKey(String likerUuid, String streamKey);
}
