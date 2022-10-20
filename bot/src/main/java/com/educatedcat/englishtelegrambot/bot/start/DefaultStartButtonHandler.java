package com.educatedcat.englishtelegrambot.bot.start;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class DefaultStartButtonHandler extends AbstractButtonHandler {
	private final StartKeyboardFactory startKeyboardFactory;
	private final MessageSource messageSource;
	
	public DefaultStartButtonHandler(StartKeyboardFactory startKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.START, ActionButtonType.NEXT);
		this.startKeyboardFactory = startKeyboardFactory;
		this.messageSource = messageSource;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return startKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.start", null, Locale.ENGLISH);
	}
}
