package com.educatedcat.englishtelegrambot.userproductivity.word;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WordProductivityServiceImpl implements WordProductivityService {
	private final UserProductivityRepository userProductivityRepository;
	
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
		return userProductivityRepository.findByUserIdAndWordId(userId, wordId)
		                                 .orElseGet(() -> userProductivityRepository.save(
				                                 new WordProductivity(userId, wordId)));
	}
}
