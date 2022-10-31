package com.educatedcat.englishtelegrambot.user.kafka;

import com.educatedcat.englishtelegrambot.user.user.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.config.*;
import org.springframework.kafka.core.*;

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
