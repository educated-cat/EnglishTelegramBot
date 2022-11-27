package com.educatedcat.englishtelegrambot.dictionary.word;

import org.springframework.data.jpa.repository.*;

import java.util.*;

interface WordProductivityRepository extends JpaRepository<WordProductivity, Long> {
	Optional<WordProductivity> findByUserIdAndWordId(long userId, UUID wordId);
	
	int countByUserIdAndProgress(long userId, byte progress);
	
	int countByUserIdAndProgressBetween(long userId, byte progress, byte progress2);
}
