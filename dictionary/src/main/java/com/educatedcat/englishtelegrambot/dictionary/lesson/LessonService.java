package com.educatedcat.englishtelegrambot.dictionary.lesson;

import java.util.List;

public interface LessonService {
	List<Lesson> findAllInChapterByLessonId(long id);
	
	List<Lesson> findAllByChapterId(long chapterId);
}
