package com.educatedcat.englishtelegrambot.botreceiver.start;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import lombok.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class StartKeyboardFactory extends AbstractKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		// TODO: get available actions from another class
		final List<StartButton> buttons = List.of(
				new StartButton(messageSource.getMessage("button.repeat.by.course", null, Locale.ENGLISH),
				                MenuButtonType.BY_COURSE),
				new StartButton(messageSource.getMessage("button.statistics", null, Locale.ENGLISH),
				                MenuButtonType.STATISTICS));
		return new StartKeyboard(keyboardEntryMapper, buttons, null, null);
	}
}
