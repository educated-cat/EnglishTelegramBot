package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;

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
	protected String getText(KeyboardEntry entry) {
		return statisticsTextFactory.buildText(entry);
	}
}
