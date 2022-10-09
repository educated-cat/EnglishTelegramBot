package com.educatedcat.englishtelegrambot.lesson;

import org.springframework.data.jpa.repository.*;

import java.util.*;

interface LessonRepository extends JpaRepository<Lesson, UUID> {
	List<Lesson> findAllByChapter_Id(UUID chapterId);
}
