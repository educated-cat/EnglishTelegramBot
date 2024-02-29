package com.educatedcat.englishtelegrambot.userstatistics.dictionary;

import com.educatedcat.englishtelegrambot.userstatistics.word.WordProductivityDto;
import io.micrometer.tracing.annotation.NewSpan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class DictionaryClient {
	private final WebClient dictionaryWebClient;
	
	@NewSpan("Find word productivity by user id")
	public WordProductivityDto getWordProductivity(long userId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("words", "productivity", String.valueOf(userId))
		                                                 .build())
		                          .retrieve()
		                          .bodyToMono(WordProductivityDto.class)
		                          .block();
	}
}
