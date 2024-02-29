package com.educatedcat.englishtelegrambot.botreceiver.start;

import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.ButtonMarker;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.BaseKeyboard;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntryMapper;
import jakarta.annotation.Nullable;
import org.springframework.context.MessageSource;

import java.util.List;

public class StartKeyboard extends BaseKeyboard {
	protected StartKeyboard(KeyboardEntryMapper keyboardEntryMapper, List<? extends ButtonMarker> buttons,
	                        ButtonMarker backButton, MenuButtonType backButtonIdType, MessageSource messageSource) {
		super(keyboardEntryMapper, buttons, backButton, backButtonIdType, messageSource);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return buttons.stream()
		              .map(buttonMarker -> (StartButton) buttonMarker)
		              .map(button -> new KeyboardEntry(button.menuButtonType(), button.name()))
		              .toList();
	}
	
	@Override
	@Nullable
	protected KeyboardEntry backButton() {
		return null;
	}
}
