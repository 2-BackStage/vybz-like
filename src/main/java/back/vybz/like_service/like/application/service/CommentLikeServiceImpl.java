package back.vybz.like_service.like.application.service;


import back.vybz.like_service.like.domain.mongodb.CommentLike;
import back.vybz.like_service.like.dto.request.RequestCommentLikeDto;
import back.vybz.like_service.like.dto.response.ResponseCommentLikeDto;
import back.vybz.like_service.like.infrastructure.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    /*
        * 댓글 좋아요 토글
     */
    @Override
    @Transactional
    public ResponseCommentLikeDto toggleCommentLike(RequestCommentLikeDto requestCommentLikeDto) {
        String commentId = requestCommentLikeDto.getCommentId();
        String likerUuid = requestCommentLikeDto.getLikerUuid();

        Optional<CommentLike> existingLike = commentLikeRepository.findByCommentIdAndLikerUuid(commentId, likerUuid);

        boolean liked;

        if (existingLike.isPresent()) {
            commentLikeRepository.deleteById(existingLike.get().getId());
            liked = false;
        } else {
            CommentLike newLike = CommentLike.builder()
                    .commentId(commentId)
                    .likerUuid(likerUuid)
                    .likerType(requestCommentLikeDto.getLikerType())
                    .writerUuid(requestCommentLikeDto.getWriterUuid())
                    .writerType(requestCommentLikeDto.getWriterType())
                    .parentCommentId(requestCommentLikeDto.getParentCommentId())
                    .build();

            commentLikeRepository.save(newLike);
            liked = true;
        }

        return ResponseCommentLikeDto.builder()
                .commentId(commentId)
                .likerUuid(likerUuid)
                .liked(liked)
                .createdAt(Instant.now())
                .build();
    }

}