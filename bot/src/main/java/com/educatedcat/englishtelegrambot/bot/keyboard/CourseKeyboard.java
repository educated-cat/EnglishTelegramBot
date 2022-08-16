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
public class CourseKeyboard extends BaseKeyboard {
	private final MessageSource messageSource;
	
	@Autowired
	protected CourseKeyboard(ObjectMapper objectMapper, MessageSource messageSource) {
		super(objectMapper);
		this.messageSource = messageSource;
	}
	
	@Override
	protected List<Map.Entry<MenuButtonType, Object>> buttons() {
		return Arrays.stream(Course.values())
		             .map((Function<Course, Map.Entry<MenuButtonType, Object>>) course ->
				             new AbstractMap.SimpleEntry<>(MenuButtonType.COURSE, course.getName()))
		             .toList();
	}
	
	@Override
	@SneakyThrows
	protected InlineKeyboardButton backButton() {
		return InlineKeyboardButton.builder()
		                           .text(messageSource.getMessage("button.back.message", null, Locale.ENGLISH))
		                           .callbackData(objectMapper.writeValueAsString(
				                           new ButtonCallback(MenuButtonType.START, null)))
		                           .build();
	}
}
