package com.educatedcat.englishtelegrambot.botreceiver.by_course;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;
import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.course.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
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
	protected String getText(BotResponse response) {
		return messageSource.getMessage("page.course", null, Locale.ENGLISH);
	}
}
