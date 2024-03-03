package com.educatedcat.englishtelegrambot.botreceiver.start;

import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
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
		return new StartKeyboard(keyboardEntryMapper, buttons, null, null, messageSource);
	}
}
