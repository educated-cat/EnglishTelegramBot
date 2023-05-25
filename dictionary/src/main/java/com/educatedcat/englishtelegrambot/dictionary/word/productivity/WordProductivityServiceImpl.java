package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

import com.educatedcat.englishtelegrambot.dictionary.*;
import com.educatedcat.englishtelegrambot.dictionary.user.productivity.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WordProductivityServiceImpl implements WordProductivityService {
	private final WordProductivityRepository wordProductivityRepository;
	private final DictionaryProperties dictionaryProperties;
	
	@Override
	@Transactional
	public void increaseWordProductivity(long userId, UUID wordId) {
		WordProductivity productivity = findByUserIdAndWordId(userId, wordId);
		productivity.increaseProgress();
	}
	
	@Override
	@Transactional
	public void decreaseWordProductivity(long userId, UUID wordId) {
		WordProductivity productivity = findByUserIdAndWordId(userId, wordId);
		productivity.decreaseProgress();
	}
	
	@Override
	@Transactional(readOnly = true)
	public WordProductivityDto getByUserId(long userId) {
		int fullyLearnedWords = wordProductivityRepository.countByUserIdAndProgress(userId, (byte) 100);
		int partlyLearnedWords = wordProductivityRepository.countByUserIdAndProgressBetween(userId, (byte) 1,
		                                                                                    (byte) 99);
		int notLearnedWords = wordProductivityRepository.countByUserIdAndProgress(userId, (byte) 0);
		return new WordProductivityDto(fullyLearnedWords, partlyLearnedWords, notLearnedWords);
	}
	
	@Override
	@Transactional
	public void updateStatuses() {
		final int successDayCountToLearn = dictionaryProperties.getWord().getProductivity().getRepeating()
		                                                       .getSuccessDayCountToLearn();
		final LocalDateTime dateTimeToRepeatLearnedWords = LocalDateTime.now().minus(
				dictionaryProperties.getWord().getProductivity().getRepeating().getDowntimeLearnedDayCount());
		
		wordProductivityRepository.updateRepeatingStatuses(successDayCountToLearn, dateTimeToRepeatLearnedWords);
	}
	
	private WordProductivity findByUserIdAndWordId(long userId, UUID wordId) {
		return wordProductivityRepository.findByUserIdAndWordId(userId, wordId)
		                                 .orElseGet(() -> wordProductivityRepository.save(
				                                 new WordProductivity(userId, wordId)));
	}
}
