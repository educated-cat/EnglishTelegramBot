package com.educatedcat.englishtelegrambot.botsender.lesson;

import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
import com.educatedcat.englishtelegrambot.botsender.word.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

@Component
public class DefaultLessonButtonHandler extends AbstractButtonHandler {
	private final WordKeyboardFactory wordKeyboardFactory;
	private final WordTextFactory wordTextFactory;
	
	public DefaultLessonButtonHandler(WordKeyboardFactory wordKeyboardFactory, WordTextFactory wordTextFactory) {
		super(MenuButtonType.LESSON, ActionButtonType.NEXT);
		this.wordKeyboardFactory = wordKeyboardFactory;
		this.wordTextFactory = wordTextFactory;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return wordKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText(KeyboardEntry entry) {
		return wordTextFactory.buildText(entry);
	}
}
