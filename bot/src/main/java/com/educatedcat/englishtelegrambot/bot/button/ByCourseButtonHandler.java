package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ByCourseButtonHandler extends AbstractButtonHandler {
	private final CourseKeyboard courseKeyboard;
	private final MessageSource messageSource;
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.course", null, Locale.ENGLISH);
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return courseKeyboard;
	}
}
