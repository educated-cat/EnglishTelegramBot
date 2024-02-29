package com.educatedcat.englishtelegrambot.botreceiver.word.end;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import com.educatedcat.englishtelegrambot.botreceiver.button.AbstractButtonHandler;
import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Locale;

@Component
public class EndWordButtonHandler extends AbstractButtonHandler {
	private final EndWordKeyboardFactory endWordKeyboardFactory;
	private final MessageSource messageSource;
	
	public EndWordButtonHandler(EndWordKeyboardFactory endWordKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.END_WORD, ActionButtonType.NEXT);
		this.endWordKeyboardFactory = endWordKeyboardFactory;
		this.messageSource = messageSource;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return endWordKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText(BotResponse response) {
		return messageSource.getMessage("page.word.end", null, Locale.ENGLISH);
	}
}
