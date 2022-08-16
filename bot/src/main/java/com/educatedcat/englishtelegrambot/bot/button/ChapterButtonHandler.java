package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class ChapterButtonHandler extends AbstractButtonHandler {
	private final LessonKeyboard lessonKeyboard;
	private final MessageSource messageSource;
	
	@Autowired
	public ChapterButtonHandler(LessonKeyboard lessonKeyboard, MessageSource messageSource) {
		this.lessonKeyboard = lessonKeyboard;
		this.messageSource = messageSource;
	}
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.lesson", null, Locale.ENGLISH);
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return lessonKeyboard;
	}
}
