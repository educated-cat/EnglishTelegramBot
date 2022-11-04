package com.educatedcat.englishtelegrambot.botsender.bot;

import com.educatedcat.englishtelegrambot.botsender.message.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotReceiverListener {
	private final Map<Class<? extends BotApiMethod<?>>, AbstractMessageSender> abstractMessageSenderMap;
	
	@KafkaListener(topics = "${kafka.message-sender.topic.name}")
	public void getUpdates(BotApiMethod<?> message) {
		abstractMessageSenderMap.get(message.getClass()).send(message);
	}
}
