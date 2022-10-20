package com.educatedcat.englishtelegrambot.bot.message;

import org.telegram.telegrambots.meta.api.objects.*;

@FunctionalInterface
public interface MessageHandler {
	void handle(Update update);
}
