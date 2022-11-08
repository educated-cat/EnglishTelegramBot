package com.educatedcat.englishtelegrambot.dictionary.word;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WordProductivityServiceImpl implements WordProductivityService {
	private final WordProductivityRepository wordProductivityRepository;
	
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
	
	private WordProductivity findByUserIdAndWordId(long userId, UUID wordId) {
		return wordProductivityRepository.findByUserIdAndWordId(userId, wordId)
		                                 .orElseGet(() -> wordProductivityRepository.save(
				                                 new WordProductivity(userId, wordId)));
	}
}
