package back.vybz.like_service.like.application.service;


import back.vybz.like_service.kafka.event.FeedLikeCountEvent;
import back.vybz.like_service.kafka.producer.FeedLikeCountKafkaProducer;
import back.vybz.like_service.like.domain.mongodb.FeedLike;
import back.vybz.like_service.like.dto.request.RequestFeedLikeDto;
import back.vybz.like_service.like.dto.response.ResponseFeedLikeDto;
import back.vybz.like_service.like.infrastructure.FeedLikeRepository;
import back.vybz.like_service.like.vo.response.ResponseFeedLikeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedLikeServiceImpl implements FeedLikeService {

    private final FeedLikeRepository feedLikeRepository;
    private final FeedLikeCountKafkaProducer feedLikeCountKafkaProducer;

    /*
     * 피드 좋아요 토글
     */
    @Override
    @Transactional
    public ResponseFeedLikeDto toggleFeedLike(RequestFeedLikeDto requestFeedLikeDto) {
        String feedId = requestFeedLikeDto.getFeedId();
        String likerUuid = requestFeedLikeDto.getLikerUuid();

        Optional<FeedLike> existingLike = feedLikeRepository.findByFeedIdAndLikerUuid(feedId, likerUuid);

        boolean liked;

        if (existingLike.isPresent()){
            feedLikeRepository.deleteById(existingLike.get().getId());
            liked = false;

            feedLikeCountKafkaProducer.send(
                    FeedLikeCountEvent.builder()
                            .feedId(feedId)
                            .delta(-1)
                            .build()
            );
        }else {
            FeedLike newLike = FeedLike.builder()
                    .feedId(feedId)
                    .likerUuid(likerUuid)
                    .build();
            feedLikeRepository.save(newLike);
            liked = true;
            feedLikeCountKafkaProducer.send(
                    FeedLikeCountEvent.builder()
                            .feedId(feedId)
                            .delta(1)
                            .build()
            );
        }
        return ResponseFeedLikeDto.builder()
                .feedId(feedId)
                .likerUuid(likerUuid)
                .liked(liked)
                .createdAt(Instant.now())
                .build();
    }

}
