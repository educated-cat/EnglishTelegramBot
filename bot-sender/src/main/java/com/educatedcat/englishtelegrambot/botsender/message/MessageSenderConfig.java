package com.educatedcat.englishtelegrambot.botsender.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class MessageSenderConfig {
	
	@Bean
	public Map<Class<? extends BotApiMethod<?>>, AbstractMessageSender> abstractMessageSenderMap(
			List<AbstractMessageSender> messageSenders) {
		return messageSenders.stream()
		                     .collect(Collectors.toMap(AbstractMessageSender::getApiMethod, Function.identity()));
	}
}
