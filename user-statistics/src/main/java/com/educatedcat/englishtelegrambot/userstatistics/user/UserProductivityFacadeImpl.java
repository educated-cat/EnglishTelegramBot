package com.educatedcat.englishtelegrambot.userstatistics.user;

import com.educatedcat.englishtelegrambot.userstatistics.word.WordProductivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProductivityFacadeImpl implements UserProductivityFacade {
	private final WordProductivityService wordProductivityService;
	
	@Override
	public UserProductivityDto prepareUserProductivity(long userId) {
		var wordProductivity = wordProductivityService.getByUserId(userId);
		return new UserProductivityDto(wordProductivity);
	}
}
