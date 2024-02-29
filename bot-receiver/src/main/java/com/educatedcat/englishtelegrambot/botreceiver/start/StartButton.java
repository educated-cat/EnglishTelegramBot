package com.educatedcat.englishtelegrambot.botreceiver.start;

import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.ButtonMarker;

public record StartButton(String name, MenuButtonType menuButtonType) implements ButtonMarker {
	public StartButton(String name) {
		this(name, null);
	}
}
