package com.educatedcat.englishtelegrambot.dictionary.lesson;

import java.util.*;

public interface LessonService {
	List<Lesson> findAllInChapterByLessonId(UUID id);
	
	List<Lesson> findAllByChapterId(UUID chapterId);
}
