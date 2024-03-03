package com.educatedcat.englishtelegrambot.dictionary.user.productivity;

import com.educatedcat.englishtelegrambot.dictionary.word.UpdateWordProductivityDto;

public interface UserProductivityFacade {
	void updateUserProductivity(UpdateWordProductivityDto dto);
}
