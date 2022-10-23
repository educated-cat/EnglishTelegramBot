package com.educatedcat.englishtelegrambot.bot.start;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.context.*;

import javax.annotation.*;
import java.util.*;

public class StartKeyboard extends BaseKeyboard {
	protected StartKeyboard(KeyboardEntryMapper keyboardEntryMapper, MessageSource messageSource,
	                        List<? extends ButtonMarker> buttons, ButtonMarker backButton,
	                        MenuButtonType backButtonIdType) {
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
