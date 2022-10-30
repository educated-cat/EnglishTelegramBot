package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import java.util.*;

public interface UserProductivityService {
	void increaseUserProductivity(long userId, UUID wordId);
	
	void decreaseUserProductivity(long userId, UUID wordId);
}
