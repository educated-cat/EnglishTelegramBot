package com.educatedcat.englishtelegrambot.dictionary.kafka;

import com.educatedcat.englishtelegrambot.dictionary.word.productivity.*;
import com.educatedcat.englishtelegrambot.kafkalib.serializer.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.*;
import org.springframework.kafka.core.*;
import org.springframework.lang.*;

import java.util.*;

public class UserProductivityConsumerFactory extends DefaultKafkaConsumerFactory<Long, UpdateWordProductivityDto> {
	public UserProductivityConsumerFactory(Map<String, Object> configs) {
		super(configs);
		setKeyDeserializer(new LongDeserializer());
		setValueDeserializer(new CustomJsonDeserializer<>(UpdateWordProductivityDto.class, false));
	}
	
	@NonNull
	@Override
	public Consumer<Long, UpdateWordProductivityDto> createConsumer() {
		return super.createConsumer();
	}
}
