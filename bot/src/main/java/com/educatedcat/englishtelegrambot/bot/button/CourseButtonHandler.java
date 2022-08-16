package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class CourseButtonHandler extends AbstractButtonHandler {
	private final ChapterKeyboard chapterKeyboard;
	private final MessageSource messageSource;
	
	@Autowired
	public CourseButtonHandler(ChapterKeyboard chapterKeyboard, MessageSource messageSource) {
		this.chapterKeyboard = chapterKeyboard;
		this.messageSource = messageSource;
	}
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.chapter", null, Locale.ENGLISH);
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return chapterKeyboard;
	}
}
