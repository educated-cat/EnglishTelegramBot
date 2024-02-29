package com.educatedcat.englishtelegrambot.botreceiver.user.productivity;

import com.educatedcat.englishtelegrambot.kafkalib.serializer.CustomJsonSerializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

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
