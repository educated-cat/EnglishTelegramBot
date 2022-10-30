package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import lombok.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class UserProductivityFacadeImpl implements UserProductivityFacade {
	private final UserProductivityService userProductivityService;
	
	@Override
	public void updateUserProductivity(UserProductivityDto dto) {
		switch (dto.wordActionType()) {
			case KNOW -> userProductivityService.increaseUserProductivity(dto.userId(), dto.wordId());
			case DONT_KNOW -> userProductivityService.decreaseUserProductivity(dto.userId(), dto.wordId());
		}
	}
}
