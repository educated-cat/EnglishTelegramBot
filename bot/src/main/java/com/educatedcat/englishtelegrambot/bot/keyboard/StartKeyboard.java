package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.dictionary.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.context.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.*;

import java.util.*;
import java.util.function.*;

public class StartKeyboard extends BaseKeyboard {
	protected StartKeyboard(ObjectMapper objectMapper, MessageSource messageSource,
	                        List<? extends ButtonMarker> buttons) {
		super(objectMapper, messageSource, buttons);
	}
	
	@Override
	protected List<Map.Entry<MenuButtonType, Object>> buttons() {
		return buttons.stream()
		              .map((Function<ButtonMarker, Map.Entry<MenuButtonType, Object>>) dto -> new AbstractMap.SimpleEntry<>(
				              MenuButtonType.BY_COURSE, "By Course"))
		              .toList();
	}
	
	@Override
	protected InlineKeyboardButton backButton() {
		return null;
	}
}
