package com.educatedcat.englishtelegrambot.bot.callback;

import com.educatedcat.englishtelegrambot.bot.course.*;
import lombok.*;
import org.springframework.lang.NonNull;
import org.telegram.telegrambots.meta.api.objects.*;

@Data
public class BotResponse {
	private final Message message;
	private final CallbackQuery callbackQuery;
	private final ButtonCallback callback;
	
	public BotResponse(@NonNull Update update) {
		this.message = update.getMessage();
		this.callbackQuery = update.getCallbackQuery();
		this.callback = null;
	}
	
	public BotResponse(@NonNull Update update, @NonNull ButtonCallback callback) {
		if (!update.hasCallbackQuery()) {
			throw new IllegalArgumentException("CallbackQuery is not exists");
		}
		this.message = update.getCallbackQuery().getMessage();
		this.callbackQuery = update.getCallbackQuery();
		this.callback = callback;
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
	
	public boolean hasCallbackQuery() {
		return callbackQuery != null;
	}
	
	public boolean editable() {
		return hasCallbackQuery() && message.getMessageId() != null;
	}
}
