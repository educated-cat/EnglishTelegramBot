package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

@Component
public class LessonButtonHandler extends AbstractButtonHandler {
	private final WordKeyboard wordKeyboard;
	
	@Autowired
	public LessonButtonHandler(WordKeyboard wordKeyboard) {
		this.wordKeyboard = wordKeyboard;
	}
	
	@Override
	protected String getText() {
		return "...";
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return wordKeyboard;
	}
}
