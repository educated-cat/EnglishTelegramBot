package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import com.educatedcat.englishtelegrambot.botreceiver.bot.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

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
