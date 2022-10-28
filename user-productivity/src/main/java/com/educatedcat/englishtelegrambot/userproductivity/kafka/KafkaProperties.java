package com.educatedcat.englishtelegrambot.userproductivity.kafka;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;

import javax.validation.constraints.*;

@Data
@Validated
@Component
@ConfigurationProperties("kafka")
public class KafkaProperties {
	@NotNull
	private Topic topic;
	
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
