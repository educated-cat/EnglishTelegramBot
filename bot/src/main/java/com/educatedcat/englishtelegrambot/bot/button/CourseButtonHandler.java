package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CourseButtonHandler extends AbstractButtonHandler {
	private final ChapterKeyboard chapterKeyboard;
	private final MessageSource messageSource;
	
	@Override
	protected String getText() {
		return messageSource.getMessage("page.chapter", null, Locale.ENGLISH);
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return chapterKeyboard;
	}
}
