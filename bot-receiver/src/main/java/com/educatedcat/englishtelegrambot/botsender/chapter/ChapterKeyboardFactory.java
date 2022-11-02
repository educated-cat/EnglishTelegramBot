package com.educatedcat.englishtelegrambot.botsender.chapter;

import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.course.*;
import com.educatedcat.englishtelegrambot.botsender.dictionary.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
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
		final List<ChapterDto> buttons;
		if (entry.actionType() == ActionButtonType.NEXT) {
			buttons = dictionaryClient.findChaptersInCourse(entry.id());
		} else {
			if (entry.idType() == MenuButtonType.CHAPTER) {
				buttons = dictionaryClient.findChaptersInCourseById(entry.id());
			} else {
				buttons = dictionaryClient.findChaptersInCourseByLessonId(entry.id());
			}
		}
		return new ChapterKeyboard(keyboardEntryMapper, buttons,
		                           new CourseDto(entry.id(),
		                                         messageSource.getMessage("button.back.message", null, Locale.ENGLISH)),
		                           entry.idType());
	}
}
