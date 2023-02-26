package com.educatedcat.englishtelegrambot.botreceiver;

import com.educatedcat.englishtelegrambot.botreceiver.user.*;
import com.educatedcat.englishtelegrambot.botreceiver.user.productivity.*;
import com.educatedcat.englishtelegrambot.kafkalib.serializer.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;

@Configuration
public class TestKafkaConfig {
	@Bean(destroyMethod = "close")
	@Primary
	public KafkaConsumer<Long, UserProductivityDto> userProductivityDtoKafkaConsumer(
			ConsumerFactory<?, ?> consumerFactory) {
		return new KafkaConsumer<>(consumerFactory.getConfigurationProperties(), new LongDeserializer(),
		                           new CustomJsonDeserializer<>(UserProductivityDto.class, false));
	}
	
	@Bean(destroyMethod = "close")
	@Primary
	public KafkaConsumer<Long, UserDto> userDtoKafkaConsumer(
			ConsumerFactory<?, ?> consumerFactory) {
		return new KafkaConsumer<>(consumerFactory.getConfigurationProperties(), new LongDeserializer(),
		                           new CustomJsonDeserializer<>(UserDto.class, false));
	}
}
