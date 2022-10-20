package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.bot.*;
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
			return new EditMessageText(getText()) {{
				setChatId(response.chatId());
				setMessageId(response.messageId());
				setReplyMarkup(getKeyboard());
			}};
		} else {
			return new SendMessage(response.chatId().toString(), getText()) {{
				setReplyMarkup(getKeyboard());
			}};
		}
	}
	
	protected abstract InlineKeyboardMarkup getKeyboard();
	
	protected abstract String getText();
}
