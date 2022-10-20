package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ChapterKeyboardFactory extends AbstractCallbackKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	private final DictionaryClient dictionaryClient;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		final List<ChapterDto> buttons = dictionaryClient.findChaptersInCourse(entry.id());
		return new ChapterKeyboard(keyboardEntryMapper, messageSource, buttons);
	}
}
