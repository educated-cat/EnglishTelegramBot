package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.DictionaryClient;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.AbstractKeyboardFactory;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.BaseKeyboard;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntryMapper;
import com.educatedcat.englishtelegrambot.botreceiver.lesson.LessonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
		final long lessonId;
		if (entry.idType() == MenuButtonType.LESSON) {
			word = dictionaryClient.findFirstWordInLesson(entry.id());
			lessonId = entry.id();
		} else {
			word = dictionaryClient.findNextWord(entry.id());
			lessonId = entry.lessonId();
		}
		final List<WordAction> actions = Arrays.stream(WordActionType.values())
		                                       .map(actionType -> new WordAction(word.id(), actionType, lessonId))
		                                       .toList();
		return new WordKeyboard(keyboardEntryMapper, actions,
		                        new LessonDto(lessonId, messageSource.getMessage("button.back.message", null, Locale.ENGLISH)),
		                        entry.idType(), messageSource);
	}
}
