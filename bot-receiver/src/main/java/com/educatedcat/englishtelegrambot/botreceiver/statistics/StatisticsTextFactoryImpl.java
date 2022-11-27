package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;
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
	public String buildText(KeyboardEntry entry) {
		// TODO: get user id
		var res = statisticsClient.getStatistics(255442971L);
		return statisticsTextFormatter.format(res);
	}
}
