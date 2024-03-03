package com.educatedcat.englishtelegrambot.botreceiver.chapter;

import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.course.CourseDto;
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
		                           entry.idType(), messageSource);
	}
}
