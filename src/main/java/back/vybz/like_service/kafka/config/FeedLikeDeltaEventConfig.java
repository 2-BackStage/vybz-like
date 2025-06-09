package back.vybz.like_service.kafka.config;

import back.vybz.like_service.kafka.event.FeedLikeDeltaEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class FeedLikeDeltaEventConfig {

    private final CommonKafkaProducerConfig commonKafkaProducerConfig;

    @Bean
    public ProducerFactory<String, FeedLikeDeltaEvent> feedLikeDeltaEventProducerFactory() {
        return new DefaultKafkaProducerFactory<>(commonKafkaProducerConfig.producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, FeedLikeDeltaEvent> feedLikeDeltaEventKafkaTemplate() {
        return new KafkaTemplate<>(feedLikeDeltaEventProducerFactory());
    }
}
