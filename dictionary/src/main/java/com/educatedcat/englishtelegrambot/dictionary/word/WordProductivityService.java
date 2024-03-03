package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.user.productivity.WordProductivityDto;

public interface WordProductivityService {
	void increaseWordProductivity(long userId, long wordId);
	
	void decreaseWordProductivity(long userId, long wordId);
	
	WordProductivityDto getByUserId(long userId);
}
