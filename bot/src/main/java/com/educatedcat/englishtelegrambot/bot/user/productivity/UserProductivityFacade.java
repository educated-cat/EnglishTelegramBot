package com.educatedcat.englishtelegrambot.bot.user.productivity;

import com.educatedcat.englishtelegrambot.bot.word.*;

import java.util.*;

public interface UserProductivityFacade {
	void updateUserProductivity(Long userId, UUID wordId, WordActionType wordActionType);
}
