package com.educatedcat.englishtelegrambot.kafkalib.serializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomJsonSerializer<T> extends JsonSerializer<T> {
	@Override
	public byte[] serialize(String topic, T data) {
		try {
			return super.serialize(topic, data);
		} catch (Exception e) {
			log.error("Unable to serialize message", e);
		}
		return new byte[0];
	}
}
