package com.educatedcat.englishtelegrambot.userproductivity.kafka;

import com.educatedcat.englishtelegrambot.userproductivity.user.productivity.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.config.*;
import org.springframework.kafka.core.*;

@Configuration
public class KafkaConfig {
	@Bean
	public ConcurrentKafkaListenerContainerFactory<Long, UserProductivityDto> kafkaListenerContainerFactory(
			ConsumerFactory<Long, UserProductivityDto> defaultFactory) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Long, UserProductivityDto>();
		factory.setConsumerFactory(new UserProductivityConsumerFactory(defaultFactory.getConfigurationProperties()));
		return factory;
	}
	
}
