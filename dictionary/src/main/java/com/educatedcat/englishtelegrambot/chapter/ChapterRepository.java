package com.educatedcat.englishtelegrambot.chapter;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

interface ChapterRepository extends JpaRepository<Chapter, UUID> {
	List<Chapter> findAllByCourse_Id(UUID courseId);
	
	@Query("SELECT c FROM Chapter AS c WHERE c.course.id IN (SELECT c2.course.id FROM Chapter AS c2 WHERE c.id = :chapterId)")
	List<Chapter> findAllInCourseByChapter_Id(@Param("chapterId") UUID chapterId);
	
	@Query("SELECT c FROM Chapter AS c WHERE c.id = (SELECT l.chapter.id FROM Lesson AS l WHERE l.id = :lessonId)")
	List<Chapter> findAllByLessonId(@Param("lessonId") UUID lessonId);
}
