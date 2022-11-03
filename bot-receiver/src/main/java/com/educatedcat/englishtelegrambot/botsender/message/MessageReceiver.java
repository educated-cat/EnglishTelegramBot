package com.educatedcat.englishtelegrambot.botsender.message;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import org.telegram.telegrambots.meta.api.methods.updates.*;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.*;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageReceiver {
	private final WebClient botWebClient;
	private final MessageHandler messageHandler;
	
	@Scheduled(fixedDelayString = "${telegram.receiver.delay}")
	void getUpdatesScheduled() {
		getUpdates().forEach(messageHandler::handle);
	}
	
	private List<Update> getUpdates() {
		var getUpdatesRequest = GetUpdates.builder().limit(1000).build();
		var response = botWebClient.get().uri("/getUpdates")
		                           .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class))
		                           .block();
		try {
			return getUpdatesRequest.deserializeResponse(response);
		} catch (TelegramApiRequestException e) {
			log.error("", e);
			return Collections.emptyList();
		}
	}
}
