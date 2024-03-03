package com.educatedcat.englishtelegrambot.botsender.bot;

import com.educatedcat.englishtelegrambot.botsender.message.AbstractMessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotReceiverListener {
	private final Map<Class<? extends BotApiMethod<?>>, AbstractMessageSender> abstractMessageSenderMap;
	
	@KafkaListener(topics = "${kafka.message-sender.topic.name}")
	public void getUpdates(BotApiMethod<?> message) {
		if (message == null) {
			log.warn("Message is null: {}", BotApiMethod.class.getSimpleName());
			return;
		}
		abstractMessageSenderMap.get(message.getClass()).send(message);
	}
}
