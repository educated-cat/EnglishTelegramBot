package com.educatedcat.englishtelegrambot.botsender.message;

import org.telegram.telegrambots.meta.api.methods.*;

public interface MessageSender {
	void sendMessage(BotApiMethod<?> message);
}
