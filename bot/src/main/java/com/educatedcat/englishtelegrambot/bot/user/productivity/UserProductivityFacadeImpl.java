package com.educatedcat.englishtelegrambot.bot.user.productivity;

import com.educatedcat.englishtelegrambot.bot.word.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserProductivityFacadeImpl implements UserProductivityFacade {
	private final UserProductivityService userProductivityService;
	
	@Override
	public void updateUserProductivity(Long userId, UUID wordId, WordActionType wordActionType) {
		switch (wordActionType) {
			case KNOW -> userProductivityService.increaseUserProductivity(userId, wordId);
			case DONT_KNOW -> userProductivityService.decreaseUserProductivity(userId, wordId);
		}
	}
}
