package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.fasterxml.jackson.databind.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ChapterKeyboardFactory extends AbstractCallbackKeyboardFactory {
	private final MessageSource messageSource;
	private final ObjectMapper objectMapper;
	
	@Override
	public BaseKeyboard build() {
		final List<ChapterDto> buttons = List.of(new ChapterDto(null, "Introduction"),
		                                         new ChapterDto(null, "Chapter 1"),
		                                         new ChapterDto(null, "Chapter 2"),
		                                         new ChapterDto(null, "Chapter 3"),
		                                         new ChapterDto(null, "You've done it!"));
		return new ChapterKeyboard(objectMapper, messageSource, buttons);
	}
}
