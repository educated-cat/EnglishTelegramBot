package com.educatedcat.englishtelegrambot.botreceiver.offset;

import java.util.Optional;

public interface BotOffsetService {
	Optional<BotOffset> getCurrentOffset();
	
	void updateCurrentOffset(long newOffset);
}
