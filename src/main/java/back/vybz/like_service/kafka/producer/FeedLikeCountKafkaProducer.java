package back.vybz.like_service.kafka.producer;

import back.vybz.like_service.kafka.event.FeedLikeCountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedLikeCountKafkaProducer {
    private final KafkaTemplate<String, FeedLikeCountEvent> feedLikeCountKafkaTemplate;
    private static final String TOPIC_NAME = "feed-like-count";

    public void send(FeedLikeCountEvent event) {
        log.info("[Kafka] Sending FeedLikeCountEvent to topic '{}': {}", TOPIC_NAME, event);

        CompletableFuture<SendResult<String, FeedLikeCountEvent>> future =
                feedLikeCountKafkaTemplate.send(TOPIC_NAME, event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("[Kafka] Failed to send FeedLikeCountEvent: {}", ex.getMessage(), ex);
            } else {
                log.info("[Kafka] Successfully sent FeedLikeCountEvent with offset: {}", result.getRecordMetadata().offset());
            }
        });
    }
}
