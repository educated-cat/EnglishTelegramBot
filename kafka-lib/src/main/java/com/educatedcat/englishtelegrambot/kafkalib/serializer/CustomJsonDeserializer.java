package com.educatedcat.englishtelegrambot.kafkalib.serializer;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import lombok.extern.slf4j.*;
import org.apache.kafka.common.header.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.lang.*;
import org.springframework.stereotype.*;

@Slf4j
@Component
public class CustomJsonDeserializer<T> extends JsonDeserializer<T> {
	public CustomJsonDeserializer() {
		super();
	}
	
	public CustomJsonDeserializer(ObjectMapper objectMapper) {
		super(objectMapper);
	}
	
	public CustomJsonDeserializer(Class<? super T> targetType) {
		super(targetType);
	}
	
	public CustomJsonDeserializer(TypeReference<? super T> targetType) {
		super(targetType);
	}
	
	public CustomJsonDeserializer(JavaType targetType) {
		super(targetType);
	}
	
	public CustomJsonDeserializer(Class<? super T> targetType, boolean useHeadersIfPresent) {
		super(targetType, useHeadersIfPresent);
	}
	
	public CustomJsonDeserializer(TypeReference<? super T> targetType, boolean useHeadersIfPresent) {
		super(targetType, useHeadersIfPresent);
	}
	
	public CustomJsonDeserializer(JavaType targetType, boolean useHeadersIfPresent) {
		super(targetType, useHeadersIfPresent);
	}
	
	public CustomJsonDeserializer(Class<? super T> targetType, ObjectMapper objectMapper) {
		super(targetType, objectMapper);
	}
	
	public CustomJsonDeserializer(TypeReference<? super T> targetType, ObjectMapper objectMapper) {
		super(targetType, objectMapper);
	}
	
	public CustomJsonDeserializer(JavaType targetType, ObjectMapper objectMapper) {
		super(targetType, objectMapper);
	}
	
	public CustomJsonDeserializer(Class<? super T> targetType, ObjectMapper objectMapper, boolean useHeadersIfPresent) {
		super(targetType, objectMapper, useHeadersIfPresent);
	}
	
	public CustomJsonDeserializer(TypeReference<? super T> targetType, ObjectMapper objectMapper,
	                              boolean useHeadersIfPresent) {
		super(targetType, objectMapper, useHeadersIfPresent);
	}
	
	public CustomJsonDeserializer(JavaType targetType, ObjectMapper objectMapper, boolean useHeadersIfPresent) {
		super(targetType, objectMapper, useHeadersIfPresent);
	}
	
	@Override
	@Nullable
	public T deserialize(String topic, Headers headers, byte[] data) {
		try {
			if (isEmpty(data)) {
				return null;
			}
			return super.deserialize(topic, headers, data);
		} catch (Exception e) {
			handleError(e);
		}
		return null;
	}
	
	@Override
	@Nullable
	public T deserialize(String topic, byte[] data) {
		try {
			if (isEmpty(data)) {
				return null;
			}
			return super.deserialize(topic, data);
		} catch (Exception e) {
			handleError(e);
		}
		return null;
	}
	
	private boolean isEmpty(byte[] data) {
		return data == null || data.length == 0;
	}
	
	private void handleError(Exception e) {
		log.error("Unable to deserialize message", e);
	}
}
