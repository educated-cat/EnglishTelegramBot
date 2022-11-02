package com.educatedcat.englishtelegrambot.botsender.start;

import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.dictionary.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;

import javax.annotation.*;
import java.util.*;

public class StartKeyboard extends BaseKeyboard {
	protected StartKeyboard(KeyboardEntryMapper keyboardEntryMapper, List<? extends ButtonMarker> buttons,
	                        ButtonMarker backButton, MenuButtonType backButtonIdType) {
		super(keyboardEntryMapper, buttons, backButton, backButtonIdType);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return buttons.stream()
		              .map(button -> new KeyboardEntry(MenuButtonType.BY_COURSE, "By Course"))
		              .toList();
	}
	
	@Override
	@Nullable
	protected KeyboardEntry backButton() {
		return null;
	}
}