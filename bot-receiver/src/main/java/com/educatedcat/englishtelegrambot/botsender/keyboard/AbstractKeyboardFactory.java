package com.educatedcat.englishtelegrambot.botsender.keyboard;

import lombok.*;

@RequiredArgsConstructor
public abstract class AbstractKeyboardFactory {
	public abstract BaseKeyboard build(KeyboardEntry entry);
}
