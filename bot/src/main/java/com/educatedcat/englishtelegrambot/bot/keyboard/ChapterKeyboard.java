package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.course.*;
import com.fasterxml.jackson.databind.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.*;

import java.util.*;
import java.util.function.*;

@Component
public class ChapterKeyboard extends BaseKeyboard {
	// TODO: get from database
	private static final List<String> chapters = List.of("Introduction", "Chapter 1", "Chapter 2", "Chapter 3",
	                                                     "You've done it!");
	
	private final MessageSource messageSource;
	
	@Autowired
	protected ChapterKeyboard(ObjectMapper objectMapper, MessageSource messageSource) {
		super(objectMapper);
		this.messageSource = messageSource;
	}
	
	@Override
	protected List<Map.Entry<MenuButtonType, Object>> buttons() {
		return chapters.stream()
		               .map((Function<String, Map.Entry<MenuButtonType, Object>>) s ->
				               new AbstractMap.SimpleEntry<>(MenuButtonType.CHAPTER, s))
		               .toList();
	}
	
	@Override
	@SneakyThrows
	protected InlineKeyboardButton backButton() {
		return InlineKeyboardButton.builder()
		                           .text(messageSource.getMessage("button.back.message", null, Locale.ENGLISH))
		                           .callbackData(objectMapper.writeValueAsString(
				                           new ButtonCallback(MenuButtonType.BY_COURSE, null)))
		                           .build();
	}
}
