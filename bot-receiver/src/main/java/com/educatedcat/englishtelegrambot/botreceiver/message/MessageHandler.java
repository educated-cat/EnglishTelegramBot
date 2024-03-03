package com.educatedcat.englishtelegrambot.botreceiver.message;

import org.telegram.telegrambots.meta.api.objects.Update;

@FunctionalInterface
public interface MessageHandler {
	void handle(Update update);
}
