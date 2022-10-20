package com.educatedcat.englishtelegrambot.bot.keyboard;

import lombok.*;

@RequiredArgsConstructor
public abstract class AbstractCallbackKeyboardFactory {
	public abstract BaseKeyboard build(KeyboardEntry entry);
}
