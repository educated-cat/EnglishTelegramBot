package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.time.*;
import java.util.*;

interface WordProductivityRepository extends JpaRepository<WordProductivity, Long> {
	Optional<WordProductivity> findByUserIdAndWordId(long userId, UUID wordId);
	
	int countByUserIdAndProgress(long userId, byte progress);
	
	int countByUserIdAndProgressBetween(long userId, byte progress, byte progress2);
	
	@Query("SELECT wp FROM WordProductivity AS wp " +
	       "WHERE wp.repeatingStatus = com.educatedcat.englishtelegrambot.dictionary.word.productivity.RepeatingStatus.TO_LEARN")
	Page<WordProductivity> findToUpdateStatuses(Pageable pageable);
	
	@Modifying
	@Query("UPDATE WordProductivity AS wp " +
	       "SET wp.repeatingStatus = CASE " +
	       "           WHEN wp.repeatingStatus = 'TO_LEARN' " +
	       "               THEN 'TO_LEARN' " +
	       "           WHEN wp.repeatingStatus = 'KNOWN' " +
	       "               THEN CASE " +
	       "                        WHEN wp.successAttemptsDayCount < :successDayCountToLearn " +
	       "                            THEN 'TO_LEARN' " +
	       "                        ELSE 'LEARNED' END " +
	       "           WHEN wp.repeatingStatus = 'LEARNED' THEN CASE " +
	       "                                                        WHEN wp.learnTimestamp < :repeatingTimestamp" +
	       "                                                            THEN 'TO_LEARN' " +
	       "                                                        ELSE 'LEARNED' END " +
	       "END")
	void updateRepeatingStatuses(@Param("successDayCountToLearn") int successDayCountToLearn,
	                             @Param("repeatingTimestamp") LocalDateTime repeatingTimestamp);
}
