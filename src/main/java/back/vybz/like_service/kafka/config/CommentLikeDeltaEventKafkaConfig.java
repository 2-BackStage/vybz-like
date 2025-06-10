package back.vybz.like_service.kafka.config;

import back.vybz.like_service.kafka.event.CommentLikeDeltaEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class CommentLikeDeltaEventKafkaConfig {

    private final CommonKafkaProducerConfig commonKafkaProducerConfig;

    @Bean
    public ProducerFactory<String, CommentLikeDeltaEvent> commentLikeDeltaEventProducerFactory() {
        return new DefaultKafkaProducerFactory<>(commonKafkaProducerConfig.producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, CommentLikeDeltaEvent> commentLikeCountKafkaTemplate() {
        return new KafkaTemplate<>(commentLikeDeltaEventProducerFactory());
    }
}
