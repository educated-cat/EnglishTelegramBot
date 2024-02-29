package com.educatedcat.englishtelegrambot.botreceiver.message;

import com.educatedcat.englishtelegrambot.botreceiver.offset.BotOffsetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.updates.GetUpdates;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.List;

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

		try {
			var response = botWebClient.get()
			                           .uri(uriBuilder -> uriBuilder.pathSegment("getUpdates")
			                                                        .queryParam("offset", currentOffset + 1)
			                                                        .queryParam("allowed_updates",
			                                                                    List.of("message", "callback_query")
			                                                                        .toString())
			                                                        .build())
			                           .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class))
			                           .block();
			var updatesDeserializer = GetUpdates.builder().build();
			final List<Update> updates = updatesDeserializer.deserializeResponse(response);
			final Integer lastUpdateId = getLastUpdateId(currentOffset, updates);
			botOffsetService.updateCurrentOffset(lastUpdateId);
			return updates;
		} catch (Exception e) {
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
