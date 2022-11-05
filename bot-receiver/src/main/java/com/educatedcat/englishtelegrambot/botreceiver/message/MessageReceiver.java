package com.educatedcat.englishtelegrambot.botreceiver.message;

import com.educatedcat.englishtelegrambot.botreceiver.offset.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import org.telegram.telegrambots.meta.api.methods.updates.*;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.*;

import java.util.*;

@Profile({"!test"})
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageReceiver {
	private final WebClient botWebClient;
	private final MessageHandler messageHandler;
	private final BotOffsetService botOffsetService;
	
	@Scheduled(fixedDelayString = "${telegram.receiver.delay}")
	void getUpdatesScheduled() {
		getUpdates().forEach(messageHandler::handle);
	}
	
	private List<Update> getUpdates() {
		final int currentOffset = botOffsetService.getCurrentOffset()
		                                          .orElseThrow()
		                                          .getOffset()
		                                          .intValue();
		var response = botWebClient.get()
		                           .uri(uriBuilder -> uriBuilder.pathSegment("getUpdates")
		                                                        .queryParam("offset", currentOffset + 1)
		                                                        .queryParam("allowed_updates",
		                                                                    List.of("message", "callback_query")
		                                                                        .toString())
		                                                        .build())
		                           .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class))
		                           .block();
		try {
			var updatesDeserializer = GetUpdates.builder().build();
			final List<Update> updates = updatesDeserializer.deserializeResponse(response);
			final Integer lastUpdateId = getLastUpdateId(currentOffset, updates);
			botOffsetService.updateCurrentOffset(lastUpdateId);
			return updates;
		} catch (TelegramApiRequestException e) {
			log.error("", e);
			return Collections.emptyList();
		}
	}
	
	private Integer getLastUpdateId(Integer currentOffset, List<Update> updates) {
		int lastUpdateId = currentOffset;
		for (Update update : updates) {
			lastUpdateId = Math.max(lastUpdateId, update.getUpdateId());
		}
		return lastUpdateId;
	}
}
