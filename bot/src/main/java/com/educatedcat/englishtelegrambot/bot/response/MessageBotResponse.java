package com.educatedcat.englishtelegrambot.bot.response;

import lombok.*;
import org.telegram.telegrambots.meta.api.objects.*;

@Data
public class MessageBotResponse {
	private final Message message;
	
	public MessageBotResponse(Update update) {
		if (update.hasMessage()) {
			this.message = update.getMessage();
		} else if (update.hasCallbackQuery()) {
			this.message = update.getCallbackQuery().getMessage();
		} else {
			throw new UnsupportedOperationException("Operation not supported");
		}
	}
	
	public Long chatId() {
		return message.getChatId();
	}
	
	public Integer messageId() {
		return message.getMessageId();
	}
	
	public boolean hasMessage() {
		return message != null;
	}
	
	public boolean isCommand() {
		if (!hasMessage()) {
			return false;
		} else {
			return message.isCommand();
		}
	}
	
	public boolean editable() {
		return hasMessage() && message.getMessageId() != null && !message.isCommand();
	}
}
