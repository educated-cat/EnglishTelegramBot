package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class PreviousLessonButtonHandler extends AbstractButtonHandler {
	private final LessonKeyboardFactory lessonKeyboardFactory;
	private final MessageSource messageSource;
	
	public PreviousLessonButtonHandler(LessonKeyboardFactory lessonKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.LESSON, ActionButtonType.PREVIOUS);
		this.lessonKeyboardFactory = lessonKeyboardFactory;
		this.messageSource = messageSource;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return lessonKeyboardFactory.build();
	}
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.lesson", null, Locale.ENGLISH);
	}
}
