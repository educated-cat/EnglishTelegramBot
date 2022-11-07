package com.educatedcat.englishtelegrambot.userproductivity.kafka;

import com.educatedcat.englishtelegrambot.userproductivity.word.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.*;
import org.springframework.lang.*;

import java.util.*;

public class UserProductivityConsumerFactory extends DefaultKafkaConsumerFactory<Long, WordProductivityDto> {
	public UserProductivityConsumerFactory(Map<String, Object> configs) {
		super(configs);
		setKeyDeserializer(new LongDeserializer());
		setValueDeserializer(new JsonDeserializer<>(WordProductivityDto.class, false));
	}
	
	@NonNull
	@Override
	public Consumer<Long, WordProductivityDto> createConsumer() {
		return super.createConsumer();
	}
}
