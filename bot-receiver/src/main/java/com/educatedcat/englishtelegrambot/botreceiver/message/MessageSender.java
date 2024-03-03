package com.educatedcat.englishtelegrambot.botreceiver.message;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface MessageSender {
	void send(BotApiMethod<?> message);
}
