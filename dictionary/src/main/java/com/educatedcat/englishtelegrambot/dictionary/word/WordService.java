package com.educatedcat.englishtelegrambot.dictionary.word;

import java.util.List;
import java.util.Optional;

public interface WordService {
	Optional<Word> find(long id);
	
	List<Word> findAllByLessonId(long lessonId);
	
	Word save(WordDto dto);
	
	Optional<Word> findFirstInLessonByLessonId(long lessonId);
	
	Optional<Word> findNext(long previousWordId);
	
	void update(long id, WordDto dto);
}
