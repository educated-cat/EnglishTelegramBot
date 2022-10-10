package com.educatedcat.englishtelegrambot.word;

import java.util.*;

public interface WordService {
	Optional<Word> find(UUID uuid);
	
	List<Word> findAllByLessonId(UUID lessonId);
	
	Word save(WordDto dto);
	
	void update(UUID uuid, WordDto dto);
}
