package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import lombok.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.*;

import javax.annotation.*;
import java.util.*;

public abstract class BaseKeyboard extends InlineKeyboardMarkup {
	protected final KeyboardEntryMapper keyboardEntryMapper;
	protected final List<? extends ButtonMarker> buttons;
	protected final ButtonMarker backButton;
	protected final MenuButtonType backButtonIdType;
	
	protected BaseKeyboard(KeyboardEntryMapper keyboardEntryMapper,
	                       List<? extends ButtonMarker> buttons, ButtonMarker backButton,
	                       MenuButtonType backButtonIdType) {
		this.keyboardEntryMapper = keyboardEntryMapper;
		this.buttons = buttons;
		this.backButton = backButton;
		this.backButtonIdType = backButtonIdType;
		
		initialize();
	}
	
	@SneakyThrows
	private void initialize() {
		List<List<InlineKeyboardButton>> keyboard = new LinkedList<>();
		List<InlineKeyboardButton> row = new LinkedList<>();
		for (int i = 0; i < buttons().size(); i++) {
			row.add(InlineKeyboardButton.builder()
			                            .text(buttons().get(i).name())
			                            .callbackData(keyboardEntryMapper.serialize(buttons().get(i)))
			                            .build());
			if (i % 3 == 0 && i != 0) {
				keyboard.add(row);
				row = new LinkedList<>();
			}
		}
		if (!keyboard.contains(row)) {
			keyboard.add(row);
		}
		KeyboardEntry back = backButton();
		if (back != null) {
			keyboard.add(List.of(InlineKeyboardButton.builder()
			                                         .text(back.name())
			                                         .callbackData(keyboardEntryMapper.serialize(back))
			                                         .build()));
		}
		setKeyboard(keyboard);
	}
	
	protected abstract List<KeyboardEntry> buttons();
	
	@Nullable
	protected abstract KeyboardEntry backButton();
}
