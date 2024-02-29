package com.educatedcat.englishtelegrambot.dictionary.word;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface WordRepository extends CrudRepository<Word, Long> {
	List<Word> findAllByLessons_Id(long lessonId);
	
	Optional<Word> findFirstByLessonsId(long lessonId);
	
	@Query("SELECT w FROM Word as w WHERE w.index > :previousWordIndex")
	Page<Word> findNext(@Param("previousWordIndex") long previousWordIndex, Pageable pageable);
}
