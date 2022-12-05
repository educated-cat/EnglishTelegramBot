package com.educatedcat.englishtelegrambot.botreceiver.user.productivity;

import org.apache.kafka.common.serialization.*;
import org.springframework.boot.autoconfigure.kafka.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.*;

@Configuration
public class UserProductivityKafkaConfig {
	@Bean
	public KafkaTemplate<Long, ?> jsonKafkaTemplate(
			KafkaProperties kafkaProperties) {
		return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(
				kafkaProperties.buildProducerProperties(), new LongSerializer(), new JsonSerializer<>())
		);
	}
}
