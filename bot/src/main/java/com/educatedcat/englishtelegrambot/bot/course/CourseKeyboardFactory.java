package com.educatedcat.englishtelegrambot.bot.course;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CourseKeyboardFactory extends AbstractKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	private final DictionaryClient dictionaryClient;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		final List<CourseDto> buttons = dictionaryClient.findCourses();
		return new CourseKeyboard(keyboardEntryMapper, buttons,
		                          new CourseDto(entry.id(),
		                                        messageSource.getMessage("button.back.message", null, Locale.ENGLISH)),
		                          MenuButtonType.COURSE);
	}
}
