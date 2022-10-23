package com.educatedcat.englishtelegrambot.chapter;

import java.util.*;

public interface ChapterService {
	List<Chapter> findChaptersById(UUID id);
	
	List<Chapter> findAllByCourseId(UUID courseId);
	
	List<Chapter> findAllByLessonId(UUID lessonId);
}
