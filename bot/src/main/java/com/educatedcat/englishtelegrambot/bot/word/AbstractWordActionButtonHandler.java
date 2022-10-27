package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.bot.*;
import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import com.educatedcat.englishtelegrambot.bot.user.productivity.*;
import lombok.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

@Getter
public abstract class AbstractWordActionButtonHandler extends AbstractButtonHandler {
	private final WordActionType wordActionType;
	private final WordKeyboardFactory wordKeyboardFactory;
	private final WordTextFactory wordTextFactory;
	private final UserProductivityFacade userProductivityFacade;
	
	public AbstractWordActionButtonHandler(WordActionType wordActionType, WordKeyboardFactory wordKeyboardFactory,
	                                       WordTextFactory wordTextFactory,
	                                       UserProductivityFacade userProductivityFacade) {
		super(MenuButtonType.WORD, ActionButtonType.NEXT);
		this.wordActionType = wordActionType;
		this.wordKeyboardFactory = wordKeyboardFactory;
		this.wordTextFactory = wordTextFactory;
		this.userProductivityFacade = userProductivityFacade;
	}
	
	@Override
	public BotApiMethod<?> handle(BotResponse response) {
		userProductivityFacade.updateUserProductivity(response.chatId(), response.getEntry().id(),
		                                              response.getEntry().wordActionType());
		return super.handle(response);
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
