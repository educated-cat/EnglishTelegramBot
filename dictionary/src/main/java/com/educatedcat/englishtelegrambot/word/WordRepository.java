package com.educatedcat.englishtelegrambot.word;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

interface WordRepository extends CrudRepository<Word, UUID> {
	@Query("SELECT w FROM Word AS w JOIN w.lessons AS l WHERE l.id = :lessonId")
	List<Word> findAllByLessonsContaining(@Param("lessonId") UUID lessonId);
}