package com.educatedcat.englishtelegrambot.botreceiver.start;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;

public record StartButton(String name, MenuButtonType menuButtonType) implements ButtonMarker {
	public StartButton(String name) {
		this(name, null);
	}
}
