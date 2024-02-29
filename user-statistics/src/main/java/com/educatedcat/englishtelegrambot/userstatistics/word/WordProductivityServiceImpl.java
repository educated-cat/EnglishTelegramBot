package com.educatedcat.englishtelegrambot.userstatistics.word;

import com.educatedcat.englishtelegrambot.userstatistics.dictionary.DictionaryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordProductivityServiceImpl implements WordProductivityService {
	private final DictionaryClient dictionaryClient;
	
	@Override
	public WordProductivityDto getByUserId(long userId) {
		return dictionaryClient.getWordProductivity(userId);
	}
}
