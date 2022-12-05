package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.user.productivity.*;

import java.util.*;

public interface WordProductivityService {
	void increaseWordProductivity(long userId, UUID wordId);
	
	void decreaseWordProductivity(long userId, UUID wordId);
	
	WordProductivityDto getByUserId(long userId);
}
