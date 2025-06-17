package back.vybz.like_service.kafka.producer;

import back.vybz.like_service.kafka.event.LiveLikeDeltaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class LiveLikeDeltaEventProducer {

    private final KafkaTemplate<String, LiveLikeDeltaEvent> liveLikeDeltaEventKafkaTemplate;
    private static final String TOPIC_NAME = "live-like-delta-count";

    public void send(LiveLikeDeltaEvent event){
        log.info("[Kafka] sending LiveLikeDeltaEvent to topic '{}': {}", TOPIC_NAME, event);

        CompletableFuture<SendResult<String, LiveLikeDeltaEvent>> future =
                liveLikeDeltaEventKafkaTemplate.send(TOPIC_NAME, event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("[Kafka] Failed to send LiveLikeDeltaEvent: {}", ex.getMessage(), ex);
            } else {
                log.info("[Kafka] Successfully sent LiveLikeDeltaEvent with offset: {}", result.getRecordMetadata().offset());
            }
        });
    }
}
