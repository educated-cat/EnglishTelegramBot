package com.educatedcat.englishtelegrambot.botsender.word.end;

import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
import com.educatedcat.englishtelegrambot.botsender.start.*;
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
		                           new StartButton(messageSource.getMessage("button.menu.start",
		                                                                    null, Locale.ENGLISH)));
	}
}
