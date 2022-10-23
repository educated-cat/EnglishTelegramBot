package com.educatedcat.englishtelegrambot.bot.lesson;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

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
			buttons = dictionaryClient.findLessonsInChapterById(entry.id());
		}
		return new LessonKeyboard(keyboardEntryMapper, buttons,
		                          new ChapterDto(entry.id(),
		                                         messageSource.getMessage("button.back.message", null, Locale.ENGLISH)),
		                          entry.idType());
	}
}
