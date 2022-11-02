package com.educatedcat.englishtelegrambot.botsender.course;

import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.chapter.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class DefaultCourseButtonHandler extends AbstractButtonHandler {
	private final ChapterKeyboardFactory chapterKeyboardFactory;
	private final MessageSource messageSource;
	
	public DefaultCourseButtonHandler(ChapterKeyboardFactory chapterKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.COURSE, ActionButtonType.NEXT);
		this.chapterKeyboardFactory = chapterKeyboardFactory;
		this.messageSource = messageSource;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return chapterKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText(KeyboardEntry entry) {
		return messageSource.getMessage("page.chapter", null, Locale.ENGLISH);
	}
}
