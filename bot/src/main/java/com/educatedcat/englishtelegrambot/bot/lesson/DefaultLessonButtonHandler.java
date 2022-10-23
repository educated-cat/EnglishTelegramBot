package com.educatedcat.englishtelegrambot.bot.lesson;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import com.educatedcat.englishtelegrambot.bot.word.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

@Component
public class DefaultLessonButtonHandler extends AbstractButtonHandler {
	private final WordKeyboardFactory wordKeyboardFactory;
	
	public DefaultLessonButtonHandler(WordKeyboardFactory wordKeyboardFactory) {
		super(MenuButtonType.LESSON, ActionButtonType.NEXT);
		this.wordKeyboardFactory = wordKeyboardFactory;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return wordKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText() {
		return "null";
	}
}
