package back.vybz.like_service.like.application.service;

import back.vybz.like_service.common.exception.BaseException;
import back.vybz.like_service.common.exception.BaseResponseStatus;
import back.vybz.like_service.common.util.LiveLikeRedisService;
import back.vybz.like_service.like.domain.mongodb.LiveLike;
import back.vybz.like_service.like.dto.request.RequestLiveLikeDto;
import back.vybz.like_service.like.infrastructure.LiveLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@RequiredArgsConstructor
public class LiveLikeServiceImpl implements LiveLikeService{

    private final LiveLikeRepository liveLikeRepository;
    private final LiveLikeRedisService liveLikeRedisService;

    @Override
    @Transactional
    public void likeLiveStream(RequestLiveLikeDto requestLiveLikeDto){

        boolean isLiked = liveLikeRepository.existsByLikerUuidAndStreamKey(
                requestLiveLikeDto.getLikerUuid(),
                requestLiveLikeDto.getStreamKey()
        );
        if (isLiked){
            throw new BaseException(BaseResponseStatus.DUPLICATE_LIVE_LIKE);
        }

        LiveLike liveLike = LiveLike.builder()
                .likerUuid(requestLiveLikeDto.getLikerUuid())
                .streamKey(requestLiveLikeDto.getStreamKey())
                .build();
        liveLikeRepository.save(liveLike);

        liveLikeRedisService.increment(requestLiveLikeDto.getStreamKey());
    }


}
