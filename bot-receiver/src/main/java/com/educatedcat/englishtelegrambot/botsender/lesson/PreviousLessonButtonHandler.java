package com.educatedcat.englishtelegrambot.botsender.lesson;

import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.chapter.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class PreviousLessonButtonHandler extends AbstractButtonHandler {
	private final ChapterKeyboardFactory chapterKeyboardFactory;
	private final MessageSource messageSource;
	
	public PreviousLessonButtonHandler(ChapterKeyboardFactory chapterKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.LESSON, ActionButtonType.PREVIOUS);
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