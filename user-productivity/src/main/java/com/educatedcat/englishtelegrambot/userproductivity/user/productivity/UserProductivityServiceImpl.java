package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserProductivityServiceImpl implements UserProductivityService {
	private final UserProductivityRepository userProductivityRepository;
	
	@Override
	@Transactional
	public void increaseUserProductivity(long userId, UUID wordId) {
		UserProductivity productivity = findByUserIdAndWordId(userId, wordId);
		productivity.increaseProgress();
	}
	
	@Override
	@Transactional
	public void decreaseUserProductivity(long userId, UUID wordId) {
		UserProductivity productivity = findByUserIdAndWordId(userId, wordId);
		productivity.decreaseProgress();
	}
	
	private UserProductivity findByUserIdAndWordId(long userId, UUID wordId) {
		return userProductivityRepository.findByUserIdAndWordId(userId, wordId)
		                                 .orElseGet(() -> userProductivityRepository.save(
				                                 new UserProductivity(userId, wordId)));
	}
}
