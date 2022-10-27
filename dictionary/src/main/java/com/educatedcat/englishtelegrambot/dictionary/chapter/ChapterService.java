package com.educatedcat.englishtelegrambot.dictionary.chapter;

import java.util.*;

public interface ChapterService {
	List<Chapter> findChaptersById(UUID id);
	
	List<Chapter> findAllByCourseId(UUID courseId);
	
	List<Chapter> findAllByLessonId(UUID lessonId);
}
