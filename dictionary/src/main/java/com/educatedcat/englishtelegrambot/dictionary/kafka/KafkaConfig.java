package com.educatedcat.englishtelegrambot.dictionary.kafka;

import com.educatedcat.englishtelegrambot.dictionary.word.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.config.*;
import org.springframework.kafka.core.*;

@Configuration
public class KafkaConfig {
	@Bean
	public ConcurrentKafkaListenerContainerFactory<Long, WordProductivityDto> kafkaListenerContainerFactory(
			ConsumerFactory<Long, WordProductivityDto> defaultFactory) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Long, WordProductivityDto>();
		factory.setConsumerFactory(new UserProductivityConsumerFactory(defaultFactory.getConfigurationProperties()));
		return factory;
	}
	
}
