package com.educatedcat.englishtelegrambot.dictionary.chapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface ChapterRepository extends JpaRepository<Chapter, Long> {
	List<Chapter> findAllByCourse_Id(long courseId);
	
	@Query("SELECT co FROM Chapter AS co WHERE co.course.id IN (SELECT ch.course.id FROM Chapter AS ch WHERE ch.id = :chapterId)")
	List<Chapter> findAllInCourseByChapter_Id(@Param("chapterId") long chapterId);
	
	@Query("SELECT c FROM Chapter AS c WHERE c.course.id = (SELECT l.chapter.course.id FROM Lesson AS l WHERE l.id = :lessonId)")
	List<Chapter> findAllInCourseByLessonId(@Param("lessonId") long lessonId);
}
