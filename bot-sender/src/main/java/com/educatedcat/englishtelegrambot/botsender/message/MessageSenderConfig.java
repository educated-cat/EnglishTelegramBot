package com.educatedcat.englishtelegrambot.botsender.message;

import org.springframework.context.annotation.*;
import org.telegram.telegrambots.meta.api.methods.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@Configuration
public class MessageSenderConfig {
	
	@Bean
	public Map<Class<? extends BotApiMethod<?>>, AbstractMessageSender> abstractMessageSenderMap(
			List<AbstractMessageSender> messageSenders) {
		return messageSenders.stream()
		                     .collect(Collectors.toMap(AbstractMessageSender::getApiMethod, Function.identity()));
	}
}
