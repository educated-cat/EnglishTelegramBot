package com.educatedcat.englishtelegrambot.userstatistics.dictionary;

import com.educatedcat.englishtelegrambot.userstatistics.word.*;
import lombok.*;
import org.springframework.cloud.sleuth.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;

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
