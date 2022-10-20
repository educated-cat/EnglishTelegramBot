package com.educatedcat.englishtelegrambot.bot.callback;

import com.educatedcat.englishtelegrambot.bot.bot.*;
import org.telegram.telegrambots.meta.api.methods.*;

@FunctionalInterface
public interface CallbackHandler {
	BotApiMethod<?> handle(BotResponse response);
}
