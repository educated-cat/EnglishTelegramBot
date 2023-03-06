package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;

public interface StatisticsTextFactory {
	String buildText(BotResponse response);
}
