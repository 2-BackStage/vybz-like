package back.vybz.like_service.like.application.service;

import back.vybz.like_service.like.dto.request.RequestLiveLikeDto;

public interface LiveLikeService {

    void likeLiveStream(RequestLiveLikeDto requestLiveLikeDto);

}
