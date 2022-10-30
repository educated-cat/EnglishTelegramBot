package com.educatedcat.englishtelegrambot.dictionary.lesson;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

interface LessonRepository extends JpaRepository<Lesson, UUID> {
	List<Lesson> findAllByChapter_Id(UUID chapterId);
	
	@Query("SELECT l FROM Lesson AS l WHERE l.chapter.id IN (SELECT l2.chapter.id FROM Lesson AS l2 WHERE l.id = :lessonId)")
	List<Lesson> findAllInChapterByLesson_Id(@Param("lessonId") UUID lessonId);
}
