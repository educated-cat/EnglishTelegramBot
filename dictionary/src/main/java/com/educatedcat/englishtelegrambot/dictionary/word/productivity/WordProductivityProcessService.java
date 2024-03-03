package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

public interface WordProductivityProcessService {
	void increaseProgress(WordProductivity productivity);
	
	void decreaseProgress(WordProductivity productivity);
}
