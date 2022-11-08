package com.educatedcat.englishtelegrambot.dictionary.word;

import org.springframework.data.jpa.repository.*;

import java.util.*;

interface WordProductivityRepository extends JpaRepository<WordProductivity, Long> {
	Optional<WordProductivity> findByUserIdAndWordId(long userId, UUID wordId);
}
