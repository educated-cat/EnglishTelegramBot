package com.educatedcat.englishtelegrambot.user.kafka;

import com.educatedcat.englishtelegrambot.kafkalib.serializer.CustomJsonDeserializer;
import com.educatedcat.englishtelegrambot.user.user.UserDto;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.lang.NonNull;

import java.util.Map;

public class UserConsumerFactory extends DefaultKafkaConsumerFactory<Long, UserDto> {
	public UserConsumerFactory(Map<String, Object> configs) {
		super(configs);
		setKeyDeserializer(new LongDeserializer());
		setValueDeserializer(new CustomJsonDeserializer<>(UserDto.class, false));
	}
	
	@NonNull
	@Override
	public Consumer<Long, UserDto> createConsumer() {
		return super.createConsumer();
	}
}
