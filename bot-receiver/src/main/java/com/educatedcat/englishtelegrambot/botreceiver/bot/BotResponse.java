package com.educatedcat.englishtelegrambot.botreceiver.bot;

import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
public class BotResponse {
	private final Message message;
	private final CallbackQuery callbackQuery;
	private final KeyboardEntry entry;
	
	public BotResponse(@NonNull Update update) {
		this.message = update.getMessage();
		this.callbackQuery = update.getCallbackQuery();
		this.entry = null;
	}
	
	public BotResponse(@NonNull Update update, @NonNull KeyboardEntry entry) {
		if (!update.hasCallbackQuery()) {
			throw new IllegalArgumentException("CallbackQuery is not exists");
		}
		this.message = update.getCallbackQuery().getMessage();
		this.callbackQuery = update.getCallbackQuery();
		this.entry = entry;
	}
	
	/**
	 * @return Telegram user ID
	 */
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
