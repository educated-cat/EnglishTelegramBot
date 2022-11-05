package com.educatedcat.englishtelegrambot.botreceiver.message;

import com.educatedcat.englishtelegrambot.botreceiver.kafka.*;
import lombok.*;
import org.springframework.cloud.sleuth.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;

@Component
@RequiredArgsConstructor
public class MessageSenderImpl implements MessageSender {
	private final KafkaTemplate<Long, BotApiMethod<?>> messageKafkaTemplate;
	private final KafkaProperties kafkaProperties;
	
	// TODO: simplify
	@Override
	@NewSpan("Send message to bot-sender")
	public void send(BotApiMethod<?> message) {
		if (message instanceof SendMessage sendMessage) {
			messageKafkaTemplate.send(kafkaProperties.getMessageSender().getTopic().getName(),
			                          Long.valueOf(sendMessage.getChatId()), message);
		} else if (message instanceof EditMessageText editMessageText) {
			messageKafkaTemplate.send(kafkaProperties.getMessageSender().getTopic().getName(),
			                          Long.valueOf(editMessageText.getChatId()), message);
		} else {
			messageKafkaTemplate.send(kafkaProperties.getMessageSender().getTopic().getName(), message);
		}
	}
}
