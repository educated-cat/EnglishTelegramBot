package com.educatedcat.englishtelegrambot.dictionary.chapter;

import java.util.List;

public interface ChapterService {
	List<Chapter> findAllInCourseByChapterId(long id);
	
	List<Chapter> findAllByCourseId(long courseId);
	
	List<Chapter> findAllInCourseByLessonId(long lessonId);
}
