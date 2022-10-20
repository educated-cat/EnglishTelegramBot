package com.educatedcat.englishtelegrambot.bot.by_course;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
public class DefaultByCourseButtonHandler extends AbstractButtonHandler {
	private final CourseKeyboardFactory courseKeyboardFactory;
	private final MessageSource messageSource;
	
	public DefaultByCourseButtonHandler(CourseKeyboardFactory courseKeyboardFactory, MessageSource messageSource) {
		super(MenuButtonType.BY_COURSE, ActionButtonType.NEXT);
		this.courseKeyboardFactory = courseKeyboardFactory;
		this.messageSource = messageSource;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return courseKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.course", null, Locale.ENGLISH);
	}
}
