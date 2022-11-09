package com.educatedcat.englishtelegrambot.userstatistics.kafka;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;

import javax.validation.constraints.*;

@Data
@Validated
@Component
@ConfigurationProperties("kafka")
public class CustomKafkaProperties {
	/**
	 * Topic properties
	 */
	@NotNull
	private Topic topic;
	
	/**
	 * Kafka topic properties
	 */
	@Data
	@Validated
	public static final class Topic {
		/**
		 * Request topic name
		 */
		@NotBlank
		private String requestName;
		
		/**
		 * Reply topic name
		 */
		@NotBlank
		private String replyName;
	}
}
