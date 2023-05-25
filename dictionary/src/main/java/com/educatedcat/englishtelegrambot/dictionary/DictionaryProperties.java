package com.educatedcat.englishtelegrambot.dictionary;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;

import java.time.*;

@Data
@Validated
@Component
@ConfigurationProperties("dictionary")
public class DictionaryProperties {
	private final Word word = new Word();
	
	@Data
	@Validated
	public static class Word {
		private final Productivity productivity = new Productivity();
		
		@Data
		@Validated
		public static class Productivity {
			private final Repeating repeating = new Repeating();
			
			@Data
			@Validated
			public static class Repeating {
				private int successDayCountToLearn = 5;
				private Duration downtimeLearnedDayCount = Duration.ofDays(30);
				private String updateStatusesCron = "0 0 0 * * ?";
			}
		}
	}
}
