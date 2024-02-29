package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import com.educatedcat.englishtelegrambot.botreceiver.button.AbstractButtonHandler;
import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Component
public class DefaultStatisticsButtonHandler extends AbstractButtonHandler {
	private final StatisticsKeyboardFactory statisticsKeyboardFactory;
	private final StatisticsTextFactory statisticsTextFactory;
	
	@Autowired
	public DefaultStatisticsButtonHandler(StatisticsKeyboardFactory statisticsKeyboardFactory,
	                                      StatisticsTextFactory statisticsTextFactory) {
		super(MenuButtonType.STATISTICS, ActionButtonType.NEXT);
		this.statisticsKeyboardFactory = statisticsKeyboardFactory;
		this.statisticsTextFactory = statisticsTextFactory;
	}
	
	@Override
	protected InlineKeyboardMarkup getKeyboard(KeyboardEntry entry) {
		return statisticsKeyboardFactory.build(entry);
	}
	
	@Override
	protected String getText(BotResponse response) {
		return statisticsTextFactory.buildText(response);
	}
}
