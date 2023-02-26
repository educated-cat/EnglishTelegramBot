package com.educatedcat.englishtelegrambot.botreceiver.user.productivity;

import com.educatedcat.englishtelegrambot.kafkalib.serializer.*;
import lombok.*;
import org.apache.kafka.common.serialization.*;
import org.springframework.boot.autoconfigure.kafka.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;

@Configuration
@RequiredArgsConstructor
public class UserProductivityKafkaConfig {
	private final CustomJsonSerializer<?> customJsonSerializer;
	
	@Bean
	public KafkaTemplate<Long, ?> jsonKafkaTemplate(KafkaProperties kafkaProperties) {
		return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(
				kafkaProperties.buildProducerProperties(), new LongSerializer(), customJsonSerializer)
		);
	}
}
