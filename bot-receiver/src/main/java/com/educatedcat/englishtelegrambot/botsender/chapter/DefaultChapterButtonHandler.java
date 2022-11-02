package com.educatedcat.englishtelegrambot.botsender.chapter;

import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
import com.educatedcat.englishtelegrambot.botsender.lesson.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class DefaultChapterButtonHandler extends AbstractButtonHandler {
	private final LessonKeyboardFactory lessonKeyboardFactory;
	private final MessageSource messageSource;
	
	public DefaultChapterButtonHandler(LessonKeyboardFactory lessonKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.CHAPTER, ActionButtonType.NEXT);
		this.lessonKeyboardFactory = lessonKeyboardFactory;
		this.messageSource = messageSource;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return lessonKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText(KeyboardEntry entry) {
		return messageSource.getMessage("page.lesson", null, Locale.ENGLISH);
	}
}
