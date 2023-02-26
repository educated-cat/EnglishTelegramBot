package com.educatedcat.englishtelegrambot.user.kafka;

import com.educatedcat.englishtelegrambot.kafkalib.serializer.*;
import com.educatedcat.englishtelegrambot.user.user.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.*;
import org.springframework.kafka.core.*;
import org.springframework.lang.*;

import java.util.*;

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
