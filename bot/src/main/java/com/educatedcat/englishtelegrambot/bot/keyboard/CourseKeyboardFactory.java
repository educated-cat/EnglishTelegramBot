package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CourseKeyboardFactory extends AbstractCallbackKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	
	@Override
	public BaseKeyboard build() {
		final List<CourseDto> buttons = List.of(new CourseDto(null, "Beginners"),
		                                        new CourseDto(null, "Intermediate"));
		return new CourseKeyboard(keyboardEntryMapper, messageSource, buttons);
	}
}
