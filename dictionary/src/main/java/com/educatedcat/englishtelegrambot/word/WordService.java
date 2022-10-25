package com.educatedcat.englishtelegrambot.word;

import java.util.*;

public interface WordService {
	Optional<Word> find(UUID uuid);
	
	List<Word> findAllByLessonId(UUID lessonId);
	
	Word save(WordDto dto);
	
	Optional<Word> findFirstInLessonByLessonId(UUID lessonId);
	
	Optional<Word> findNext(UUID previousWordId);
	
	void update(UUID uuid, WordDto dto);
}
