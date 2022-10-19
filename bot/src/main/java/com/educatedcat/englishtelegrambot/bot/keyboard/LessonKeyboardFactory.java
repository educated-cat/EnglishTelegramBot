package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class LessonKeyboardFactory extends AbstractCallbackKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	
	@Override
	public BaseKeyboard build() {
		final List<LessonDto> buttons = List.of(new LessonDto(null, "Lesson 1"),
		                                        new LessonDto(null, "Lesson 2"),
		                                        new LessonDto(null, "Lesson 3"),
		                                        new LessonDto(null, "Lesson 4"),
		                                        new LessonDto(null, "Lesson 5"));
		return new LessonKeyboard(keyboardEntryMapper, messageSource, buttons);
	}
}
