package back.vybz.like_service.like.application.service;


import back.vybz.like_service.like.dto.request.RequestFeedLikeDto;
import back.vybz.like_service.like.dto.response.ResponseFeedLikeDto;


public interface FeedLikeService {
    ResponseFeedLikeDto toggleFeedLike(RequestFeedLikeDto requestFeedLikeDto);
}
