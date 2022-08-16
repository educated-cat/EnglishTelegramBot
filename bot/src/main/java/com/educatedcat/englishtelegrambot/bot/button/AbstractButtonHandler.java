package com.educatedcat.englishtelegrambot.bot.button;

import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

public abstract class AbstractButtonHandler implements ButtonHandler {
	@Override
	public BotApiMethod<?> execute(Update update) {
		if (canEditMessage(update)) {
			return new EditMessageText(getText()) {{
				setChatId(chatId(update));
				setMessageId(messageId(update));
				setReplyMarkup(getKeyboard());
			}};
		} else {
			return new SendMessage(chatId(update), getText()) {{
				setReplyMarkup(getKeyboard());
			}};
		}
	}
	
	protected boolean canEditMessage(Update update) {
		return (update.hasMessage() && !update.getMessage().isCommand()) &&
				(update.hasMessage() && update.getMessage().getMessageId() != null)
				|| (update.hasCallbackQuery() && update.getCallbackQuery().getMessage().getMessageId() != null);
	}
	
	protected abstract String getText();
	
	protected abstract InlineKeyboardMarkup getKeyboard();
	
	private String chatId(Update update) {
		if (update.hasMessage()) {
			return update.getMessage().getChatId().toString();
		}
		if (update.hasCallbackQuery()) {
			return update.getCallbackQuery().getMessage().getChatId().toString();
		}
		throw new IllegalArgumentException("An Update doesn't have a message");
	}
	
	private Integer messageId(Update update) {
		if (update.hasMessage()) {
			return update.getMessage().getMessageId();
		}
		if (update.hasCallbackQuery()) {
			return update.getCallbackQuery().getMessage().getMessageId();
		}
		throw new IllegalArgumentException("An Update doesn't have a message");
	}
}
