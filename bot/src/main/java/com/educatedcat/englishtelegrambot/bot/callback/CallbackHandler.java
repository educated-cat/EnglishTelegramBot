package com.educatedcat.englishtelegrambot.bot.callback;

import org.telegram.telegrambots.meta.api.methods.*;

@FunctionalInterface
public interface CallbackHandler {
	BotApiMethod<?> handle(CallbackQueryBotResponse response);
}
