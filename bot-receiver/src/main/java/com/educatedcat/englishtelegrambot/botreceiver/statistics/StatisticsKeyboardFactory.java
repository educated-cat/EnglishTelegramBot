package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.AbstractKeyboardFactory;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.BaseKeyboard;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntryMapper;
import com.educatedcat.englishtelegrambot.botreceiver.start.StartButton;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class StatisticsKeyboardFactory extends AbstractKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		return new StatisticsKeyboard(keyboardEntryMapper, Collections.emptyList(),
		                              new StartButton(messageSource.getMessage("button.back.message", null,
		                                                                       Locale.ENGLISH)),
		                              MenuButtonType.STATISTICS, messageSource);
	}
}
