package back.vybz.like_service.like.application.service;

import back.vybz.like_service.like.dto.request.RequestCommentLikeDto;
import back.vybz.like_service.like.dto.response.ResponseCommentLikeDto;

public interface CommentLikeService {
    ResponseCommentLikeDto toggleCommentLike(RequestCommentLikeDto requestCommentLikeDto);
}
