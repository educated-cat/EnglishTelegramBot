package com.educatedcat.englishtelegrambot.dictionary.user.productivity;

import com.educatedcat.englishtelegrambot.dictionary.word.UpdateWordProductivityDto;
import com.educatedcat.englishtelegrambot.dictionary.word.productivity.WordProductivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProductivityFacadeImpl implements UserProductivityFacade {
	private final WordProductivityService wordProductivityService;
	
	@Override
	public void updateUserProductivity(UpdateWordProductivityDto dto) {
		switch (dto.wordActionType()) {
			case KNOW -> wordProductivityService.increaseWordProductivity(dto.userId(), dto.wordId());
			case DONT_KNOW -> wordProductivityService.decreaseWordProductivity(dto.userId(), dto.wordId());
		}
	}
}
