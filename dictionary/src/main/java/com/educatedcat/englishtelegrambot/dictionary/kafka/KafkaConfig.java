package com.educatedcat.englishtelegrambot.dictionary.kafka;

import com.educatedcat.englishtelegrambot.dictionary.word.productivity.*;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.*;
import org.springframework.kafka.config.*;

@Configuration
public class KafkaConfig {
	@Bean
	public ConcurrentKafkaListenerContainerFactory<Long, UpdateWordProductivityDto> kafkaListenerContainerFactory(
			KafkaProperties kafkaProperties) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Long, UpdateWordProductivityDto>();
		factory.setConsumerFactory(new UserProductivityConsumerFactory(kafkaProperties.buildConsumerProperties()));
		return factory;
	}
	
}
