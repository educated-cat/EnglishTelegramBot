package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class PreviousCourseButtonHandler extends AbstractButtonHandler {
	private final StartKeyboardFactory startKeyboardFactory;
	private final MessageSource messageSource;
	
	public PreviousCourseButtonHandler(StartKeyboardFactory startKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.COURSE, ActionButtonType.PREVIOUS);
		this.startKeyboardFactory = startKeyboardFactory;
		this.messageSource = messageSource;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return startKeyboardFactory.build();
	}
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.start", null, Locale.ENGLISH);
	}
}
