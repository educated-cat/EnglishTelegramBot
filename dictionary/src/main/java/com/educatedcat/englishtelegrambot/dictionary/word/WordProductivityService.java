package com.educatedcat.englishtelegrambot.dictionary.word;

import java.util.*;

public interface WordProductivityService {
	void increaseWordProductivity(long userId, UUID wordId);
	
	void decreaseWordProductivity(long userId, UUID wordId);
}
