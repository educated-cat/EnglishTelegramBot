package com.educatedcat.englishtelegrambot.botsender.word;

import com.educatedcat.englishtelegrambot.botsender.bot.*;
import com.educatedcat.englishtelegrambot.botsender.button.*;
import com.educatedcat.englishtelegrambot.botsender.keyboard.*;
import com.educatedcat.englishtelegrambot.botsender.user.productivity.*;
import lombok.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

@Getter
public abstract class AbstractWordActionButtonHandler extends AbstractButtonHandler {
	private final WordActionType wordActionType;
	private final WordKeyboardFactory wordKeyboardFactory;
	private final WordTextFactory wordTextFactory;
	private final UserProductivityService userProductivityService;
	
	public AbstractWordActionButtonHandler(WordActionType wordActionType, WordKeyboardFactory wordKeyboardFactory,
	                                       WordTextFactory wordTextFactory,
	                                       UserProductivityService userProductivityService) {
		super(MenuButtonType.WORD, ActionButtonType.NEXT);
		this.wordActionType = wordActionType;
		this.wordKeyboardFactory = wordKeyboardFactory;
		this.wordTextFactory = wordTextFactory;
		this.userProductivityService = userProductivityService;
	}
	
	@Override
	public BotApiMethod<?> handle(BotResponse response) {
		userProductivityService.updateUserProductivity(
				new UserProductivityDto(response.chatId(), response.getEntry().id(),
				                        response.getEntry().wordActionType()));
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
