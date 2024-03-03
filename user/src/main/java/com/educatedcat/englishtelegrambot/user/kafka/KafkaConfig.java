package com.educatedcat.englishtelegrambot.user.kafka;

import com.educatedcat.englishtelegrambot.user.user.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConfig {
	@Bean
	public ConcurrentKafkaListenerContainerFactory<Long, UserDto> kafkaListenerContainerFactory(
			ConsumerFactory<Long, UserDto> defaultFactory) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Long, UserDto>();
		factory.setConsumerFactory(new UserConsumerFactory(defaultFactory.getConfigurationProperties()));
		return factory;
	}
	
}
