package com.educatedcat.englishtelegrambot.botreceiver.lesson;

import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.chapter.ChapterDto;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.DictionaryClient;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.AbstractKeyboardFactory;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.BaseKeyboard;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class LessonKeyboardFactory extends AbstractKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	private final DictionaryClient dictionaryClient;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		final List<LessonDto> buttons;
		if (entry.actionType() == ActionButtonType.NEXT) {
			buttons = dictionaryClient.findLessonsInChapter(entry.id());
		} else {
			buttons = dictionaryClient.findLessonsInChapterById(entry.lessonId());
		}
		return new LessonKeyboard(keyboardEntryMapper, buttons,
		                          new ChapterDto(entry.id(),
		                                         messageSource.getMessage("button.back.message", null, Locale.ENGLISH)),
		                          entry.idType(), messageSource);
	}
}
