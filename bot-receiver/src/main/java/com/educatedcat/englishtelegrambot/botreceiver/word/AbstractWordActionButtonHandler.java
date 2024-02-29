package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import com.educatedcat.englishtelegrambot.botreceiver.button.AbstractButtonHandler;
import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import com.educatedcat.englishtelegrambot.botreceiver.user.productivity.UserProductivityDto;
import com.educatedcat.englishtelegrambot.botreceiver.user.productivity.UserProductivityService;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

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
	protected String getText(BotResponse response) {
		return wordTextFactory.buildText(response.getEntry());
	}
}
