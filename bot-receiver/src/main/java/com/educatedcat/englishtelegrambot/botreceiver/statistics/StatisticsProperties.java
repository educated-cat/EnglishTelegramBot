package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
