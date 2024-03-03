package com.educatedcat.englishtelegrambot.botsender.kafka;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Component
@ConfigurationProperties("kafka")
public class KafkaProperties {
	@NotNull
	private MessageSender messageSender;
	
	/**
	 * Bot message sender properties
	 */
	@Data
	@Validated
	public static final class MessageSender {
		/**
		 * Topic name
		 */
		@NotNull
		private Topic topic;
	}
	
	/**
	 * Kafka topic properties
	 */
	@Data
	@Validated
	public static final class Topic {
		/**
		 * Topic name
		 */
		@NotBlank
		private String name;
	}
}
