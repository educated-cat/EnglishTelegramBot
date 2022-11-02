package com.educatedcat.englishtelegrambot.botsender.message;

import org.telegram.telegrambots.meta.api.objects.*;

@FunctionalInterface
public interface MessageHandler {
	void handle(Update update);
}
