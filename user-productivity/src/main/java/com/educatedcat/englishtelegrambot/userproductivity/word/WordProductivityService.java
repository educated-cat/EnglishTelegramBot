package com.educatedcat.englishtelegrambot.userproductivity.word;

import java.util.*;

public interface WordProductivityService {
	void increaseWordProductivity(long userId, UUID wordId);
	
	void decreaseWordProductivity(long userId, UUID wordId);
}
