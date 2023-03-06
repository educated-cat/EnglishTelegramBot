package com.educatedcat.englishtelegrambot.botreceiver.course;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;
import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import com.educatedcat.englishtelegrambot.botreceiver.start.*;
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
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return startKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText(BotResponse response) {
		return messageSource.getMessage("page.start", null, Locale.ENGLISH);
	}
}
