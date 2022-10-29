package com.educatedcat.englishtelegrambot.bot.by_course;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import com.educatedcat.englishtelegrambot.bot.start.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class PreviousByCourseButtonHandler extends AbstractButtonHandler {
	private final StartKeyboardFactory startKeyboardFactory;
	private final MessageSource messageSource;
	
	public PreviousByCourseButtonHandler(StartKeyboardFactory startKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.BY_COURSE, ActionButtonType.PREVIOUS);
		this.startKeyboardFactory = startKeyboardFactory;
		this.messageSource = messageSource;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return startKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText(KeyboardEntry entry) {
		return messageSource.getMessage("page.start", null, Locale.ENGLISH);
	}
}