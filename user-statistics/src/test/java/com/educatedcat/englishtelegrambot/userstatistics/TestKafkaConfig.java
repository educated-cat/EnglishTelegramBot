package com.educatedcat.englishtelegrambot.userstatistics;

import com.educatedcat.englishtelegrambot.userstatistics.user.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.*;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.*;

@Configuration
public class TestKafkaConfig {
	@Bean(destroyMethod = "close")
	@Primary
	public KafkaConsumer<Long, UserProductivityDto> userProductivityDtoKafkaConsumer(
			ConsumerFactory<?, ?> consumerFactory) {
		return new KafkaConsumer<>(consumerFactory.getConfigurationProperties(), new LongDeserializer(),
		                           new JsonDeserializer<>(UserProductivityDto.class, false));
	}
}
