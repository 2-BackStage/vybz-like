package back.vybz.like_service.like.application.service;


import back.vybz.like_service.kafka.event.FeedLikeDeltaEvent;
import back.vybz.like_service.kafka.producer.FeedLikeDeltaEventProducer;
import back.vybz.like_service.like.domain.mongodb.FeedLike;
import back.vybz.like_service.like.dto.request.RequestFeedLikeDto;
import back.vybz.like_service.like.dto.response.ResponseFeedLikeDto;
import back.vybz.like_service.like.infrastructure.FeedLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedLikeServiceImpl implements FeedLikeService {

    private final FeedLikeRepository feedLikeRepository;
    private final FeedLikeDeltaEventProducer feedLikeDeltaEventProducer;

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

            feedLikeDeltaEventProducer.send(
                    FeedLikeDeltaEvent.builder()
                            .feedId(feedId)
                            .feedType(requestFeedLikeDto.getFeedType())
                            .delta(-1)
                            .build()
            );
        }else {
            FeedLike newLike = FeedLike.builder()
                    .feedId(feedId)
                    .feedType(requestFeedLikeDto.getFeedType())
                    .likerUuid(likerUuid)
                    .build();
            try {
                feedLikeRepository.save(newLike);
                liked = true;
            } catch (org.springframework.dao.DuplicateKeyException e) {
                // 중복이면 이미 누군가 저장한 것, 다시 조회해서 liked=true로 처리
                liked = true;
            }
            feedLikeDeltaEventProducer.send(
                    FeedLikeDeltaEvent.builder()
                            .feedId(feedId)
                            .feedType(requestFeedLikeDto.getFeedType())
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
