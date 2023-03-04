package com.educatedcat.englishtelegrambot.botreceiver.dictionary;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;

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
