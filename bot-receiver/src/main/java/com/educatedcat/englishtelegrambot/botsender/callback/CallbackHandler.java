package com.educatedcat.englishtelegrambot.botsender.callback;

import com.educatedcat.englishtelegrambot.botsender.bot.*;
import org.telegram.telegrambots.meta.api.methods.*;

@FunctionalInterface
public interface CallbackHandler {
	BotApiMethod<?> handle(BotResponse response);
}
