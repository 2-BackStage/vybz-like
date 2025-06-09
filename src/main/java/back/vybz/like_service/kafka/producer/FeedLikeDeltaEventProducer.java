package back.vybz.like_service.kafka.producer;

import back.vybz.like_service.kafka.event.FeedLikeDeltaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedLikeDeltaEventProducer {
    private final KafkaTemplate<String, FeedLikeDeltaEvent> feedLikeDeltaEventKafkaTemplate;
    private static final String TOPIC_NAME = "feed-delta-count";

    public void send(FeedLikeDeltaEvent event) {
        log.info("[Kafka] Sending FeedLikeCountEvent to topic '{}': {}", TOPIC_NAME, event);

        CompletableFuture<SendResult<String, FeedLikeDeltaEvent>> future =
                feedLikeDeltaEventKafkaTemplate.send(TOPIC_NAME, event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("[Kafka] Failed to send FeedLikeCountEvent: {}", ex.getMessage(), ex);
            } else {
                log.info("[Kafka] Successfully sent FeedLikeCountEvent with offset: {}", result.getRecordMetadata().offset());
            }
        });
    }
}
