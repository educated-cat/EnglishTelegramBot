package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;

public interface StatisticsTextFactory {
	String buildText(KeyboardEntry entry);
}
