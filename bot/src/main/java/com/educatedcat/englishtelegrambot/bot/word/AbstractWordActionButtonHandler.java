package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

@Getter
public abstract class AbstractWordActionButtonHandler extends AbstractButtonHandler {
	private final WordActionType wordActionType;
	private final WordKeyboardFactory wordKeyboardFactory;
	private final WordTextFactory wordTextFactory;
	
	public AbstractWordActionButtonHandler(WordActionType wordActionType, WordKeyboardFactory wordKeyboardFactory,
	                                       WordTextFactory wordTextFactory) {
		super(MenuButtonType.WORD, ActionButtonType.NEXT);
		this.wordActionType = wordActionType;
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
