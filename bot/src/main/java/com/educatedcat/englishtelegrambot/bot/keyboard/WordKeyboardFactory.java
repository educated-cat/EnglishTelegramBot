package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class WordKeyboardFactory extends AbstractCallbackKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	private final DictionaryClient dictionaryClient;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		final List<WordDto> buttons = dictionaryClient.findWordsInLesson(entry.id());
		return new WordKeyboard(keyboardEntryMapper, messageSource, buttons);
	}
}
