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
public class LessonKeyboard extends BaseKeyboard {
	// TODO: get from database
	private static final List<String> lessons = List.of("Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5");
	
	private final MessageSource messageSource;
	
	@Autowired
	protected LessonKeyboard(ObjectMapper objectMapper, MessageSource messageSource) {
		super(objectMapper);
		this.messageSource = messageSource;
	}
	
	@Override
	protected List<Map.Entry<MenuButtonType, Object>> buttons() {
		return lessons.stream()
		              .map((Function<String, Map.Entry<MenuButtonType, Object>>) s ->
				              new AbstractMap.SimpleEntry<>(MenuButtonType.LESSON, s))
		              .toList();
	}
	
	@Override
	@SneakyThrows
	protected InlineKeyboardButton backButton() {
		return InlineKeyboardButton.builder()
		                           .text(messageSource.getMessage("button.back.message", null, Locale.ENGLISH))
		                           .callbackData(objectMapper.writeValueAsString(
				                           new ButtonCallback(MenuButtonType.COURSE, Course.BEGINNERS)))
		                           .build();
	}
}
