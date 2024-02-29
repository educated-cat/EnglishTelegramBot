package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.user.productivity.WordProductivityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WordProductivityServiceImpl implements WordProductivityService {
	private final WordProductivityRepository wordProductivityRepository;
	
	@Override
	@Transactional
	public void increaseWordProductivity(long userId, long wordId) {
		WordProductivity productivity = findByUserIdAndWordId(userId, wordId);
		productivity.increaseProgress();
	}
	
	@Override
	@Transactional
	public void decreaseWordProductivity(long userId, long wordId) {
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
	
	private WordProductivity findByUserIdAndWordId(long userId, long wordId) {
		return wordProductivityRepository.findByUserIdAndWordId(userId, wordId)
		                                 .orElseGet(() -> wordProductivityRepository.save(
				                                 new WordProductivity(userId, wordId)));
	}
}
