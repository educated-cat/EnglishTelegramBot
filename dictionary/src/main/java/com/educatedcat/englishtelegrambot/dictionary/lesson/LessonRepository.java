package com.educatedcat.englishtelegrambot.dictionary.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface LessonRepository extends JpaRepository<Lesson, Long> {
	List<Lesson> findAllByChapter_Id(long chapterId);
	
	@Query("SELECT l FROM Lesson AS l WHERE l.chapter.id IN (SELECT l2.chapter.id FROM Lesson AS l2 WHERE l2.id = :lessonId)")
	List<Lesson> findAllInChapterByLesson_Id(@Param("lessonId") long lessonId);
}
