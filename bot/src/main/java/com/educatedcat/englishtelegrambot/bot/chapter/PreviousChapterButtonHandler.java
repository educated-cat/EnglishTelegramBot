package com.educatedcat.englishtelegrambot.bot.chapter;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class PreviousChapterButtonHandler extends AbstractButtonHandler {
	private final ChapterKeyboardFactory chapterKeyboardFactory;
	private final MessageSource messageSource;
	
	public PreviousChapterButtonHandler(ChapterKeyboardFactory chapterKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.CHAPTER, ActionButtonType.PREVIOUS);
		this.chapterKeyboardFactory = chapterKeyboardFactory;
		this.messageSource = messageSource;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return chapterKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.chapter", null, Locale.ENGLISH);
	}
}
