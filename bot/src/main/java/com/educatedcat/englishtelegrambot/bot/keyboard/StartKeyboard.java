package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@Component
public class StartKeyboard extends BaseKeyboard {
	private final MessageSource messageSource;
	
	@Autowired
	protected StartKeyboard(ObjectMapper objectMapper, MessageSource messageSource) {
		super(objectMapper);
		this.messageSource = messageSource;
	}
	
	@Override
	protected List<Map.Entry<MenuButtonType, Object>> buttons() {
		return Stream.of(messageSource.getMessage("button.repeat.by.course", null, Locale.ENGLISH))
		             .map((Function<String, Map.Entry<MenuButtonType, Object>>) s ->
				             new AbstractMap.SimpleEntry<>(MenuButtonType.BY_COURSE, s))
		             .toList();
	}
	
	@Override
	protected InlineKeyboardButton backButton() {
		return null;
	}
}
