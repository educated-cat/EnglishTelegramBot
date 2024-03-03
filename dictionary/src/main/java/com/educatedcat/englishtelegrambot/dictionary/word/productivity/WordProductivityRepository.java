package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

interface WordProductivityRepository extends JpaRepository<WordProductivity, Long> {
	Optional<WordProductivity> findByUserIdAndWordId(long userId, long wordId);
	
	int countByUserIdAndProgress(long userId, byte progress);
	
	int countByUserIdAndProgressBetween(long userId, byte progress, byte progress2);
	
	@Query("SELECT wp FROM WordProductivity AS wp " +
			"WHERE wp.repeatingStatus = com.educatedcat.englishtelegrambot.dictionary.word.productivity.RepeatingStatus.TO_LEARN")
	Page<WordProductivity> findToUpdateStatuses(Pageable pageable);
	
	@Modifying
	@Query("UPDATE WordProductivity AS wp " +
			"SET wp.repeatingStatus = CASE " +
			"           WHEN wp.repeatingStatus = com.educatedcat.englishtelegrambot.dictionary.word.productivity.RepeatingStatus.TO_LEARN " +
			"               THEN com.educatedcat.englishtelegrambot.dictionary.word.productivity.RepeatingStatus.TO_LEARN " +
			"           WHEN wp.repeatingStatus = com.educatedcat.englishtelegrambot.dictionary.word.productivity.RepeatingStatus.KNOWN " +
			"               THEN CASE " +
			"                        WHEN wp.successAttemptsDayCount < :successDayCountToLearn " +
			"                            THEN com.educatedcat.englishtelegrambot.dictionary.word.productivity.RepeatingStatus.TO_LEARN " +
			"                        ELSE com.educatedcat.englishtelegrambot.dictionary.word.productivity.RepeatingStatus.LEARNED END " +
			"           WHEN wp.repeatingStatus = com.educatedcat.englishtelegrambot.dictionary.word.productivity.RepeatingStatus.LEARNED THEN CASE " +
			"                                                        WHEN wp.learnTimestamp < :repeatingTimestamp" +
			"                                                            THEN com.educatedcat.englishtelegrambot.dictionary.word.productivity.RepeatingStatus.TO_LEARN " +
			"                                                        ELSE com.educatedcat.englishtelegrambot.dictionary.word.productivity.RepeatingStatus.LEARNED END " +
			"END")
	void updateRepeatingStatuses(@Param("successDayCountToLearn") int successDayCountToLearn,
	                             @Param("repeatingTimestamp") LocalDateTime repeatingTimestamp);
}
