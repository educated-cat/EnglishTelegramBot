package com.educatedcat.englishtelegrambot.botreceiver.button;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import lombok.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

@Getter
public abstract class AbstractButtonHandler {
	private final MenuButtonType buttonType;
	private final ActionButtonType actionButtonType;
	
	protected AbstractButtonHandler(MenuButtonType buttonType, ActionButtonType actionButtonType) {
		this.buttonType = buttonType;
		this.actionButtonType = actionButtonType;
	}
	
	public BotApiMethod<?> handle(BotResponse response) {
		if (response.editable()) {
			final EditMessageText editMessageText = new EditMessageText(getText(response.getEntry()));
			editMessageText.setChatId(response.chatId());
			editMessageText.setMessageId(response.messageId());
			editMessageText.setReplyMarkup(getKeyboard(response.getEntry()));
			return editMessageText;
		} else {
			return new SendMessage(response.chatId()
			                               .toString(), getText(response.getEntry())) {{
				setReplyMarkup(getKeyboard(response.getEntry()));
			}};
		}
	}
	
	protected abstract InlineKeyboardMarkup getKeyboard(KeyboardEntry entry);
	
	protected abstract String getText(KeyboardEntry entry);
}
