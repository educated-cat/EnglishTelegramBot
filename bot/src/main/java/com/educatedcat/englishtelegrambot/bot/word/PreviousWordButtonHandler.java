package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

@Component
public class PreviousWordButtonHandler extends AbstractButtonHandler {
	private final WordKeyboardFactory wordKeyboardFactory;
	
	public PreviousWordButtonHandler(WordKeyboardFactory wordKeyboardFactory) {
		super(MenuButtonType.WORD, ActionButtonType.PREVIOUS);
		this.wordKeyboardFactory = wordKeyboardFactory;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard() {
		return wordKeyboardFactory.build();
	}
	
	@Override
	protected String getText() {
		return "null";
	}
}
