package com.educatedcat.englishtelegrambot.botsender.kafka;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;

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
