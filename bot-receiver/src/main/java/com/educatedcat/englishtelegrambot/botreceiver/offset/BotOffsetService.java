package com.educatedcat.englishtelegrambot.botreceiver.offset;

import java.util.*;

public interface BotOffsetService {
	Optional<BotOffset> getCurrentOffset();
	
	void updateCurrentOffset(long newOffset);
}
