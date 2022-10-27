package com.educatedcat.englishtelegrambot.bot.word.end;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import com.educatedcat.englishtelegrambot.bot.lesson.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class EndWordKeyboardFactory extends AbstractKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		return new EndWordKeyboard(keyboardEntryMapper,
		                           new LessonDto(entry.id(), messageSource.getMessage("button.back.message",
		                                                                              null, Locale.ENGLISH)),
		                           MenuButtonType.LESSON);
	}
}
