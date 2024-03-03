package com.educatedcat.englishtelegrambot.botreceiver.callback;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@FunctionalInterface
public interface CallbackHandler {
	BotApiMethod<?> handle(BotResponse response);
}
