package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import lombok.*;
import org.springframework.context.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.*;

import java.util.*;

public class WordKeyboard extends BaseKeyboard {
	protected WordKeyboard(KeyboardEntryMapper keyboardEntryMapper, MessageSource messageSource,
	                       List<? extends ButtonMarker> buttons) {
		super(keyboardEntryMapper, messageSource, buttons);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return buttons.stream()
		              .map(buttonMarker -> (WordDto) buttonMarker)
		              .map(button -> new KeyboardEntry(MenuButtonType.WORD, button.id(), button.name()))
		              .toList();
	}
	
	@Override
	@SneakyThrows
	protected InlineKeyboardButton backButton() {
		return InlineKeyboardButton.builder()
		                           .text(messageSource.getMessage("button.back.message", null, Locale.ENGLISH))
		                           .callbackData(keyboardEntryMapper.serialize(
				                           new KeyboardEntry(MenuButtonType.CHAPTER,
				                                             "Introduction"))) // TODO: get from queue
		                           .build();
	}
}
