package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.course.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.fasterxml.jackson.databind.*;
import lombok.*;
import org.springframework.context.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.*;

import java.util.*;
import java.util.function.*;

public class WordKeyboard extends BaseKeyboard {
	protected WordKeyboard(ObjectMapper objectMapper, MessageSource messageSource,
	                       List<? extends ButtonMarker> buttons) {
		super(objectMapper, messageSource, buttons);
	}
	
	@Override
	protected List<Map.Entry<MenuButtonType, Object>> buttons() {
		return buttons.stream()
		              .map((Function<ButtonMarker, Map.Entry<MenuButtonType, Object>>) dto ->
				              new AbstractMap.SimpleEntry<>(MenuButtonType.WORD, ((WordDto) dto).name()))
		              .toList();
	}
	
	@Override
	@SneakyThrows
	protected InlineKeyboardButton backButton() {
		return InlineKeyboardButton.builder()
		                           .text(messageSource.getMessage("button.back.message", null, Locale.ENGLISH))
		                           .callbackData(objectMapper.writeValueAsString(
				                           new ButtonCallback(MenuButtonType.CHAPTER,
				                                              "Introduction"))) // TODO: get from queue
		                           .build();
	}
}
