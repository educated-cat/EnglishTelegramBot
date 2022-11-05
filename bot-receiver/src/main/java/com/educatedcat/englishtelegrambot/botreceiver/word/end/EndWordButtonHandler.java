package com.educatedcat.englishtelegrambot.botreceiver.word.end;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

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
	protected String getText(KeyboardEntry entry) {
		return messageSource.getMessage("page.word.end", null, Locale.ENGLISH);
	}
}
