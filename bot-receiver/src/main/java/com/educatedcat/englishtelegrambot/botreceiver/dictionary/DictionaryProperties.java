package com.educatedcat.englishtelegrambot.botreceiver.dictionary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@ConfigurationProperties("dictionary")
public class DictionaryProperties {
	@NotNull
	private Url url;
	
	/**
	 * Dictionary URL properties
	 */
	@Data
	@Validated
	public static class Url {
		/**
		 * Dictionary api URL
		 */
		@NotBlank
		private String api;
	}
}
