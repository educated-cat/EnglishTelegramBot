package com.educatedcat.englishtelegrambot.userstatistics.word;

import com.educatedcat.englishtelegrambot.userstatistics.dictionary.*;
import lombok.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

@Service
@RequiredArgsConstructor
public class WordProductivityServiceImpl implements WordProductivityService {
	private final DictionaryClient dictionaryClient;
	
	@Override
	public Mono<WordProductivityDto> getByUserId(long userId) {
		return dictionaryClient.getWordProductivity(userId);
	}
}
