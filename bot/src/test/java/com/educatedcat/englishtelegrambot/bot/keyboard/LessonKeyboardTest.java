package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.*;
import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.course.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LessonKeyboardTest extends AbstractTest {
	@Autowired
	private LessonKeyboard lessonKeyboard;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void verifyKeyboard() {
		List<List<InlineKeyboardButton>> keyboard = lessonKeyboard.getKeyboard();
		
		assertTrue(keyboard.stream().allMatch(
				buttons -> buttons.stream().allMatch(btn -> keyboardCorrect(keyboard, buttons, btn))));
	}
	
	private boolean keyboardCorrect(List<List<InlineKeyboardButton>> keyboard,
	                                List<InlineKeyboardButton> inlineKeyboardButtons,
	                                InlineKeyboardButton inlineKeyboardButton) {
		try {
			ButtonCallback callback = objectMapper.readValue(inlineKeyboardButton.getCallbackData(),
			                                                 ButtonCallback.class);
			if (inlineKeyboardButtons.size() == 1 && keyboard.indexOf(inlineKeyboardButtons) == keyboard.size() - 1) {
				return callback.button() == MenuButtonType.COURSE;
			}
			return callback.button() == MenuButtonType.LESSON;
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}