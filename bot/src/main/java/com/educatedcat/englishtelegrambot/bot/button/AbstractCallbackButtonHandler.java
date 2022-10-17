package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.callback.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

public abstract class AbstractCallbackButtonHandler implements CallbackButtonHandler {
	@Override
	public BotApiMethod<?> execute(CallbackQueryBotResponse response) {
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
	
	protected abstract String getText();
	
	protected abstract InlineKeyboardMarkup getKeyboard();
}
