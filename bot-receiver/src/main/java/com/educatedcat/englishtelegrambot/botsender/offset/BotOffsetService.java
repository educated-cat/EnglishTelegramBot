package com.educatedcat.englishtelegrambot.botsender.offset;

import java.util.*;

public interface BotOffsetService {
	Optional<BotOffset> getCurrentOffset();
	
	void updateCurrentOffset(long newOffset);
}
