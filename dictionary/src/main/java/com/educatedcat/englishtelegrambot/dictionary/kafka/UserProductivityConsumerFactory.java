package com.educatedcat.englishtelegrambot.dictionary.kafka;

import com.educatedcat.englishtelegrambot.dictionary.word.UpdateWordProductivityDto;
import com.educatedcat.englishtelegrambot.kafkalib.serializer.CustomJsonDeserializer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.lang.NonNull;

import java.util.Map;

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
