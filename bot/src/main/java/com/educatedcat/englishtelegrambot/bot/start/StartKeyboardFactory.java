package com.educatedcat.englishtelegrambot.bot.start;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class StartKeyboardFactory extends AbstractKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		// TODO: get available actions from another class
		final List<CourseDto> buttons = List.of(new CourseDto(null, messageSource.getMessage(
				"button.repeat.by.course", null, Locale.ENGLISH)));
		return new StartKeyboard(keyboardEntryMapper, messageSource, buttons);
	}
}
