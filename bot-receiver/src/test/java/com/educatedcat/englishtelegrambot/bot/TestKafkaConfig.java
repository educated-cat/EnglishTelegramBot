package com.educatedcat.englishtelegrambot.bot;

import com.educatedcat.englishtelegrambot.botreceiver.user.*;
import com.educatedcat.englishtelegrambot.botreceiver.user.productivity.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.*;

@Configuration
public class TestKafkaConfig {
	@Bean(destroyMethod = "close")
	@Primary
	public KafkaConsumer<Long, UserProductivityDto> userProductivityDtoKafkaConsumer(
			ConsumerFactory<?, ?> consumerFactory) {
		return new KafkaConsumer<>(consumerFactory.getConfigurationProperties(), new LongDeserializer(),
		                           new JsonDeserializer<>(UserProductivityDto.class, false));
	}
	
	@Bean(destroyMethod = "close")
	@Primary
	public KafkaConsumer<Long, UserDto> userDtoKafkaConsumer(
			ConsumerFactory<?, ?> consumerFactory) {
		return new KafkaConsumer<>(consumerFactory.getConfigurationProperties(), new LongDeserializer(),
		                           new JsonDeserializer<>(UserDto.class, false));
	}
}
