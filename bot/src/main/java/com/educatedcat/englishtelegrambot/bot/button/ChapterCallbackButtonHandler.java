package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ChapterCallbackButtonHandler extends AbstractCallbackButtonHandler {
	private final LessonKeyboardFactory lessonKeyboardFactory;
	private final MessageSource messageSource;
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.lesson", null, Locale.ENGLISH);
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return lessonKeyboardFactory.build();
	}
}
