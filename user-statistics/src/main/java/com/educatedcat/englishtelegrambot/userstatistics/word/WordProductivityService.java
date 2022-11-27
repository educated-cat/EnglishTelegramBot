package com.educatedcat.englishtelegrambot.userstatistics.word;

public interface WordProductivityService {
	WordProductivityDto getByUserId(long userId);
}
