package com.educatedcat.englishtelegrambot.word;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

interface WordRepository extends CrudRepository<Word, UUID> {
	List<Word> findAllByLessons_Id(UUID lessonId);
	
	Optional<Word> findFirstByLessonsId(UUID lessonId);
	
	@Query("SELECT w FROM Word as w WHERE w.index > :previousWordIndex")
	Page<Word> findNext(@Param("previousWordIndex") long previousWordIndex, Pageable pageable);
}
