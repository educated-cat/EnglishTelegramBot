package com.educatedcat.englishtelegrambot.bot.chapter;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ChapterKeyboardFactory extends AbstractKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	private final DictionaryClient dictionaryClient;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		final List<ChapterDto> buttons = entry.actionType() == ActionButtonType.NEXT
		                                 ? dictionaryClient.findChaptersInCourse(entry.id())
		                                 : dictionaryClient.findChaptersInCourseById(entry.id());
		return new ChapterKeyboard(keyboardEntryMapper, messageSource, buttons,
		                           new CourseDto(entry.id(), "Back"));
	}
}
