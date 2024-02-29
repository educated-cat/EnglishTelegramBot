package com.educatedcat.englishtelegrambot.botreceiver.message;

import com.educatedcat.englishtelegrambot.botreceiver.kafka.CustomKafkaProperties;
import io.micrometer.tracing.annotation.NewSpan;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component
@RequiredArgsConstructor
public class MessageSenderImpl implements MessageSender {
	private final KafkaTemplate<Long, BotApiMethod<?>> messageKafkaTemplate;
	private final CustomKafkaProperties customKafkaProperties;
	
	// TODO: simplify
	@Override
	@NewSpan("Send message to bot-sender")
	public void send(BotApiMethod<?> message) {
		if (message instanceof SendMessage sendMessage) {
			messageKafkaTemplate.send(customKafkaProperties.getMessageSender().getTopic().getName(),
			                          Long.valueOf(sendMessage.getChatId()), message);
		} else if (message instanceof EditMessageText editMessageText) {
			messageKafkaTemplate.send(customKafkaProperties.getMessageSender().getTopic().getName(),
			                          Long.valueOf(editMessageText.getChatId()), message);
		} else {
			messageKafkaTemplate.send(customKafkaProperties.getMessageSender().getTopic().getName(), message);
		}
	}
}
