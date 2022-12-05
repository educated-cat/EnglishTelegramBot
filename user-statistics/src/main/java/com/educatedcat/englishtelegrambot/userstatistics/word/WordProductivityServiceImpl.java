package com.educatedcat.englishtelegrambot.userstatistics.word;

import com.educatedcat.englishtelegrambot.userstatistics.dictionary.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class WordProductivityServiceImpl implements WordProductivityService {
	private final DictionaryClient dictionaryClient;
	
	@Override
	public WordProductivityDto getByUserId(long userId) {
		return dictionaryClient.getWordProductivity(userId);
	}
}
