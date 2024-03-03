package com.educatedcat.englishtelegrambot.botreceiver.keyboard;

import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.ButtonMarker;
import jakarta.annotation.Nullable;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseKeyboard extends InlineKeyboardMarkup {
	protected final KeyboardEntryMapper keyboardEntryMapper;
	protected final List<? extends ButtonMarker> buttons;
	protected final ButtonMarker backButton;
	protected final MenuButtonType backButtonIdType;
	protected final MessageSource messageSource;
	
	protected BaseKeyboard(KeyboardEntryMapper keyboardEntryMapper,
	                       List<? extends ButtonMarker> buttons, ButtonMarker backButton,
	                       MenuButtonType backButtonIdType, MessageSource messageSource) {
		this.keyboardEntryMapper = keyboardEntryMapper;
		this.buttons = buttons;
		this.backButton = backButton;
		this.backButtonIdType = backButtonIdType;
		this.messageSource = messageSource;
		
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
