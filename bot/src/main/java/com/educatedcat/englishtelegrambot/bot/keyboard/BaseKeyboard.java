package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.course.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.*;
import org.springframework.util.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.*;

import javax.annotation.*;
import java.util.*;

public abstract class BaseKeyboard extends InlineKeyboardMarkup implements InitializingBean {
	protected final ObjectMapper objectMapper;
	
	protected BaseKeyboard(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		List<List<InlineKeyboardButton>> keyboard = new LinkedList<>();
		List<InlineKeyboardButton> row = new LinkedList<>();
		for (int i = 0; i < buttons().size(); i++) {
			row.add(InlineKeyboardButton.builder()
			                            .text(StringUtils.capitalize(buttons().get(i).getValue().toString()))
			                            .callbackData(objectMapper.writeValueAsString(
					                            new ButtonCallback(buttons().get(i).getKey(),
					                                               buttons().get(i).getValue())))
			                            .build());
			if (i % 3 == 0 && i != 0) {
				keyboard.add(row);
				row = new LinkedList<>();
			}
		}
		if (!keyboard.contains(row)) {
			keyboard.add(row);
		}
		InlineKeyboardButton back = backButton();
		if (back != null) {
			keyboard.add(List.of(back));
		}
		setKeyboard(keyboard);
	}
	
	protected abstract List<Map.Entry<MenuButtonType, Object>> buttons();
	
	@Nullable
	protected abstract InlineKeyboardButton backButton();
}
