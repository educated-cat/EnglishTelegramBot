package com.educatedcat.englishtelegrambot.botreceiver;

import com.educatedcat.englishtelegrambot.botreceiver.user.UserDto;
import com.educatedcat.englishtelegrambot.botreceiver.user.productivity.UserProductivityDto;
import com.educatedcat.englishtelegrambot.kafkalib.serializer.CustomJsonDeserializer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.ConsumerFactory;

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
