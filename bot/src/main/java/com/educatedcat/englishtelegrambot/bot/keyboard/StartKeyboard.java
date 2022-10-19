package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import org.springframework.context.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.*;

import java.util.*;

public class StartKeyboard extends BaseKeyboard {
	protected StartKeyboard(KeyboardEntryMapper keyboardEntryMapper, MessageSource messageSource,
	                        List<? extends ButtonMarker> buttons) {
		super(keyboardEntryMapper, messageSource, buttons);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return buttons.stream()
		              .map(button -> new KeyboardEntry(MenuButtonType.BY_COURSE, "By Course"))
		              .toList();
	}
	
	@Override
	protected InlineKeyboardButton backButton() {
		return null;
	}
}
