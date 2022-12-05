package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;

import javax.validation.constraints.*;

@Data
@Component
@Validated
@ConfigurationProperties("statistics")
public class StatisticsProperties {
	@NotNull
	private Url url;
	
	/**
	 * Statistics URL properties
	 */
	@Data
	@Validated
	public static class Url {
		/**
		 * Statistics api URL
		 */
		@NotBlank
		private String api;
	}
}
