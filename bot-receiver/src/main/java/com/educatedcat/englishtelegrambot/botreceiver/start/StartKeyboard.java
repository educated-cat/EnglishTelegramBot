package com.educatedcat.englishtelegrambot.botreceiver.start;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import jakarta.annotation.*;

import java.util.*;

public class StartKeyboard extends BaseKeyboard {
	protected StartKeyboard(KeyboardEntryMapper keyboardEntryMapper, List<? extends ButtonMarker> buttons,
	                        ButtonMarker backButton, MenuButtonType backButtonIdType) {
		super(keyboardEntryMapper, buttons, backButton, backButtonIdType);
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
