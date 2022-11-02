package com.educatedcat.englishtelegrambot.botsender.word;

import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.dictionary.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
import com.educatedcat.englishtelegrambot.botsender.lesson.*;
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
		// TODO: fix double searching words
		final WordDto word;
		if (entry.idType() == MenuButtonType.LESSON) {
			word = dictionaryClient.findFirstWordInLesson(entry.id());
		} else {
			word = dictionaryClient.findNextWord(entry.id());
		}
		final List<WordAction> actions = Arrays.stream(WordActionType.values())
		                                       .map(actionType -> new WordAction(word.id(), actionType))
		                                       .toList();
		return new WordKeyboard(keyboardEntryMapper, actions,
		                        new LessonDto(entry.id(),
		                                      messageSource.getMessage("button.back.message", null, Locale.ENGLISH)),
		                        entry.idType());
	}
}
