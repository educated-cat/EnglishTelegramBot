package com.educatedcat.englishtelegrambot.botreceiver.word.end;

import com.educatedcat.englishtelegrambot.botreceiver.keyboard.AbstractKeyboardFactory;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.BaseKeyboard;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntryMapper;
import com.educatedcat.englishtelegrambot.botreceiver.start.StartButton;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class EndWordKeyboardFactory extends AbstractKeyboardFactory {
	private final MessageSource messageSource;
	private final KeyboardEntryMapper keyboardEntryMapper;
	
	@Override
	public BaseKeyboard build(KeyboardEntry entry) {
		return new EndWordKeyboard(keyboardEntryMapper,
		                           new StartButton(messageSource.getMessage("button.menu.start",
		                                                                    null, Locale.ENGLISH)), messageSource);
	}
}
