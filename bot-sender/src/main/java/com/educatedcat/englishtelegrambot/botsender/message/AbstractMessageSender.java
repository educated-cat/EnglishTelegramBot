package com.educatedcat.englishtelegrambot.botsender.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractMessageSender {
	private final WebClient botWebClient;
	
	public void send(BotApiMethod<?> message) {
		try {
			botWebClient.post()
			            .uri(getApiPath())
			            .bodyValue(message)
			            .retrieve()
			            .toBodilessEntity()
			            .subscribe();
		} catch (Exception e) {
			log.error("Unable to send message, message value={}", message.getClass().getSimpleName(), e);
		}
	}
	
	abstract Class<? extends BotApiMethod<?>> getApiMethod();
	
	abstract String getApiPath();
}
