package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import com.educatedcat.englishtelegrambot.botreceiver.bot.BotResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticsTextFactoryImpl implements StatisticsTextFactory {
	private final StatisticsTextFormatter statisticsTextFormatter;
	private final StatisticsClient statisticsClient;
	
	@Override
	@SneakyThrows
	public String buildText(BotResponse response) {
		var res = statisticsClient.getStatistics(response.chatId());
		return statisticsTextFormatter.format(res);
	}
}
