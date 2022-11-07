package com.educatedcat.englishtelegrambot.userproductivity.word;

import org.springframework.data.jpa.repository.*;

import java.util.*;

interface UserProductivityRepository extends JpaRepository<WordProductivity, Long> {
	Optional<WordProductivity> findByUserIdAndWordId(long userId, UUID wordId);
}
