package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.fasterxml.jackson.databind.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class StartKeyboardFactory extends AbstractCallbackKeyboardFactory {
	private final MessageSource messageSource;
	private final ObjectMapper objectMapper;
	
	@Override
	public BaseKeyboard build() {
		// TODO: fix buttons
		final List<CourseDto> buttons = List.of(new CourseDto(null, messageSource.getMessage(
				"button.repeat.by.course", null, Locale.ENGLISH)));
		return new StartKeyboard(objectMapper, messageSource, buttons);
	}
}
