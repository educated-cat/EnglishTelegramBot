package com.educatedcat.englishtelegrambot.dictionary.user.productivity;

import com.educatedcat.englishtelegrambot.dictionary.word.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class UserProductivityFacadeImpl implements UserProductivityFacade {
	private final WordProductivityService wordProductivityService;
	
	@Override
	public void updateUserProductivity(WordProductivityDto dto) {
		switch (dto.wordActionType()) {
			case KNOW -> wordProductivityService.increaseWordProductivity(dto.userId(), dto.wordId());
			case DONT_KNOW -> wordProductivityService.decreaseWordProductivity(dto.userId(), dto.wordId());
		}
	}
}