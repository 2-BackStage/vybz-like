package back.vybz.like_service.kafka.producer;

import back.vybz.like_service.kafka.event.CommentLikeDeltaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentLikeDeltaEventKafkaProducer {

    private final KafkaTemplate<String, CommentLikeDeltaEvent> commentLikeCountKafkaTemplate;
    private static final String TOPIC_NAME = "comment-delta-count";

    public void send(CommentLikeDeltaEvent event){
        log.info("[Kafka] Sending CommentLikeCountEvent to topic '{}': {}", TOPIC_NAME, event);

        CompletableFuture<SendResult<String, CommentLikeDeltaEvent>> future =
                commentLikeCountKafkaTemplate.send(TOPIC_NAME, event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("[Kafka] Failed to send CommentLikeCountEvent: {}", ex.getMessage(), ex);
            } else {
                log.info("[Kafka] Successfully sent CommentLikeCountEvent with offset: {}", result.getRecordMetadata().offset());
            }
        });
    }
}
