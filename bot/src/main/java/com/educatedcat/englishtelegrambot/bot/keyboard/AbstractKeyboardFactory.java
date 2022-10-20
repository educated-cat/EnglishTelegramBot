package com.educatedcat.englishtelegrambot.bot.keyboard;

import lombok.*;

@RequiredArgsConstructor
public abstract class AbstractKeyboardFactory {
	public abstract BaseKeyboard build(KeyboardEntry entry);
}
