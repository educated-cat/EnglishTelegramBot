package com.educatedcat.englishtelegrambot.bot.callback;

import com.educatedcat.englishtelegrambot.bot.course.*;
import lombok.*;
import org.springframework.lang.NonNull;
import org.telegram.telegrambots.meta.api.objects.*;

@Data
public class CallbackQueryBotResponse {
	private final CallbackQuery callbackQuery;
	private final ButtonCallback callback;
	
	public CallbackQueryBotResponse(@NonNull Update update, @NonNull ButtonCallback callback) {
		if (!update.hasCallbackQuery()) {
			throw new IllegalArgumentException("CallbackQuery is not exists");
		}
		this.callbackQuery = update.getCallbackQuery();
		this.callback = callback;
	}
	
	public Long chatId() {
		return callbackQuery.getMessage().getChatId();
	}
	
	public Integer messageId() {
		return callbackQuery.getMessage().getMessageId();
	}
	
	public boolean hasMessage() {
		return hasCallbackQuery() && callbackQuery.getMessage() != null;
	}
	
	public boolean hasCallbackQuery() {
		return callbackQuery != null;
	}
	
	public boolean editable() {
		return hasCallbackQuery() && callbackQuery.getMessage().getMessageId() != null;
	}
}
