package back.vybz.like_service.kafka.config;

import back.vybz.like_service.kafka.event.LiveLikeDeltaEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class LiveLikeDeltaEventKafkaConfig {

    private final CommonKafkaProducerConfig commonKafkaProducerConfig;

    @Bean
    public ProducerFactory<String, LiveLikeDeltaEvent> liveLikeDeltaEventProducerFactory(){
        return new DefaultKafkaProducerFactory<>(commonKafkaProducerConfig.commonProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, LiveLikeDeltaEvent> liveLikeDeltaEventKafkaTemplate() {
        return new KafkaTemplate<>(liveLikeDeltaEventProducerFactory());
    }
}
