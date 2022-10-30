package com.educatedcat.englishtelegrambot.userproductivity.kafka;

import com.educatedcat.englishtelegrambot.userproductivity.user.productivity.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.*;
import org.springframework.lang.*;

import java.util.*;

public class UserProductivityConsumerFactory extends DefaultKafkaConsumerFactory<Long, UserProductivityDto> {
	public UserProductivityConsumerFactory(Map<String, Object> configs) {
		super(configs);
		setKeyDeserializer(new LongDeserializer());
		setValueDeserializer(new JsonDeserializer<>(UserProductivityDto.class, false));
	}
	
	@NonNull
	@Override
	public Consumer<Long, UserProductivityDto> createConsumer() {
		return super.createConsumer();
	}
}
