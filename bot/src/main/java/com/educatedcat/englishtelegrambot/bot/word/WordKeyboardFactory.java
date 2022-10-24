package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class WordKeyboardFactory extends AbstractKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	private final DictionaryClient dictionaryClient;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		final List<WordDto> words = dictionaryClient.findWordsInLesson(entry.id());
		return new WordKeyboard(keyboardEntryMapper, Collections.emptyList(),
		                        new LessonDto(entry.id(),
		                                      messageSource.getMessage("button.back.message", null, Locale.ENGLISH)),
		                        entry.idType());
	}
}
