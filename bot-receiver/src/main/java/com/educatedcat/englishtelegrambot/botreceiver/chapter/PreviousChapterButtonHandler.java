package com.educatedcat.englishtelegrambot.botreceiver.chapter;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;
import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.course.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class PreviousChapterButtonHandler extends AbstractButtonHandler {
	private final CourseKeyboardFactory courseKeyboardFactory;
	private final MessageSource messageSource;
	
	public PreviousChapterButtonHandler(CourseKeyboardFactory courseKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.CHAPTER, ActionButtonType.PREVIOUS);
		this.courseKeyboardFactory = courseKeyboardFactory;
		this.messageSource = messageSource;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return courseKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText(BotResponse response) {
		return messageSource.getMessage("page.course", null, Locale.ENGLISH);
	}
}
