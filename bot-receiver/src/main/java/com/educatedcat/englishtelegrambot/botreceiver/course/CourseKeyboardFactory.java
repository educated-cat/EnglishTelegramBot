package com.educatedcat.englishtelegrambot.botreceiver.course;

import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
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
public class CourseKeyboardFactory extends AbstractKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	private final DictionaryClient dictionaryClient;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		final List<CourseDto> buttons = dictionaryClient.findCourses();
		return new CourseKeyboard(keyboardEntryMapper, buttons,
		                          new CourseDto(entry.id(),
		                                        messageSource.getMessage("button.back.message", null, Locale.ENGLISH)),
		                          MenuButtonType.COURSE, messageSource);
	}
}
