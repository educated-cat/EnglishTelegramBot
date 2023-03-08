package com.educatedcat.englishtelegrambot.botreceiver.statistics;

import io.micrometer.tracing.annotation.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;

@Component
@RequiredArgsConstructor
public class StatisticsClient {
	private final WebClient statisticsWebClient;
	
	@NewSpan("Get statistics by user ID")
	public UserProductivityDto getStatistics(long userId) {
		return statisticsWebClient.get()
		                          .uri(builder -> builder.pathSegment("statistics", String.valueOf(userId)).build())
		                          .exchangeToMono(
				                          clientResponse -> clientResponse.bodyToMono(UserProductivityDto.class))
		                          .block();
	}
}
