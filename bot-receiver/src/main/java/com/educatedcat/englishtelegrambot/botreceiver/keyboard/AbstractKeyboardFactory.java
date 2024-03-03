package com.educatedcat.englishtelegrambot.botreceiver.keyboard;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractKeyboardFactory {
	public abstract BaseKeyboard build(KeyboardEntry entry);
}
