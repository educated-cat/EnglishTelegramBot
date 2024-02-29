package com.educatedcat.englishtelegrambot.botreceiver.button;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

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
			return EditMessageText.builder().chatId(response.chatId())
			                                .messageId(response.messageId())
			                                .text(getText(response))
			                                .replyMarkup(getKeyboard(response.getEntry()))
			                                .build();
		} else {
			return SendMessage.builder().chatId(response.chatId())
			                            .text(getText(response))
			                            .replyMarkup(getKeyboard(response.getEntry()))
			                            .build();
		}
	}
	
	protected abstract InlineKeyboardMarkup getKeyboard(KeyboardEntry entry);
	
	protected abstract String getText(BotResponse response);
}
