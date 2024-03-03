package com.educatedcat.englishtelegrambot.dictionary.word.productivity;

import com.educatedcat.englishtelegrambot.dictionary.DictionaryProperties;
import com.educatedcat.englishtelegrambot.dictionary.user.productivity.WordProductivityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WordProductivityServiceImpl implements WordProductivityService {
	private final WordProductivityRepository wordProductivityRepository;
	private final DictionaryProperties dictionaryProperties;
	private final WordProductivityProcessService wordProductivityProcessService;
	
	@Override
	@Transactional
	public void increaseWordProductivity(long userId, long wordId) {
		WordProductivity productivity = findByUserIdAndWordId(userId, wordId);
		wordProductivityProcessService.increaseProgress(productivity);
	}
	
	@Override
	@Transactional
	public void decreaseWordProductivity(long userId, long wordId) {
		WordProductivity productivity = findByUserIdAndWordId(userId, wordId);
		wordProductivityProcessService.decreaseProgress(productivity);
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
	
	private WordProductivity findByUserIdAndWordId(long userId, long wordId) {
		return wordProductivityRepository.findByUserIdAndWordId(userId, wordId)
		                                 .orElseGet(() -> wordProductivityRepository.save(
				                                 new WordProductivity(userId, wordId)));
	}
}
