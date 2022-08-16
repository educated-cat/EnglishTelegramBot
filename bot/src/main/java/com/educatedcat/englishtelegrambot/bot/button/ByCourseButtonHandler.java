package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class ByCourseButtonHandler extends AbstractButtonHandler {
	private final CourseKeyboard courseKeyboard;
	private final MessageSource messageSource;
	
	@Autowired
	public ByCourseButtonHandler(CourseKeyboard courseKeyboard, MessageSource messageSource) {
		this.courseKeyboard = courseKeyboard;
		this.messageSource = messageSource;
	}
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.course", null, Locale.ENGLISH);
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return courseKeyboard;
	}
}
