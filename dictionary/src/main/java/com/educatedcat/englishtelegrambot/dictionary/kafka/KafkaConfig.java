package com.educatedcat.englishtelegrambot.dictionary.kafka;

import com.educatedcat.englishtelegrambot.dictionary.word.UpdateWordProductivityDto;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

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
