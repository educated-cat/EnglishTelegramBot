package com.educatedcat.englishtelegrambot.bot;

import com.educatedcat.englishtelegrambot.bot.user.productivity.*;
import org.apache.kafka.clients.consumer.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;

@Configuration
public class TestKafkaConfig {
	@Bean(destroyMethod = "close")
	@Primary
	public KafkaConsumer<Long, UserProductivityDto> userProductivityDtoKafkaConsumer(
			ConsumerFactory<?, ?> consumerFactory) {
		return new KafkaConsumer<>(consumerFactory.getConfigurationProperties());
	}
}
