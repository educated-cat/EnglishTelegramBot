package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import lombok.*;
import org.springframework.context.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.*;

import java.util.*;

public class CourseKeyboard extends BaseKeyboard {
	protected CourseKeyboard(KeyboardEntryMapper keyboardEntryMapper, MessageSource messageSource,
	                         List<? extends ButtonMarker> buttons) {
		super(keyboardEntryMapper, messageSource, buttons);
	}
	
	@Override
	protected List<KeyboardEntry> buttons() {
		return buttons.stream()
		              .map(buttonMarker -> (CourseDto) buttonMarker)
		              .map(button -> new KeyboardEntry(MenuButtonType.COURSE, button.id(), button.name()))
		              .toList();
	}
	
	@Override
	@SneakyThrows
	protected InlineKeyboardButton backButton() {
		return InlineKeyboardButton.builder()
		                           .text(messageSource.getMessage("button.back.message", null, Locale.ENGLISH))
		                           .callbackData(keyboardEntryMapper.serialize(
				                           new KeyboardEntry(MenuButtonType.START, null)))
		                           .build();
	}
}
