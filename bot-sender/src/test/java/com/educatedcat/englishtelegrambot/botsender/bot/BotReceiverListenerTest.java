package com.educatedcat.englishtelegrambot.botsender.bot;

import com.educatedcat.englishtelegrambot.botsender.kafka.KafkaProperties;
import com.educatedcat.englishtelegrambot.botsender.message.SendMessageMessageSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.Duration;

import static org.awaitility.Awaitility.*;
import static org.mockito.BDDMockito.*;

@MockBeans({
		@MockBean(WebClient.class)
})
@EmbeddedKafka
@SpringBootTest
class BotReceiverListenerTest {
	@Autowired
	private KafkaTemplate<Long, BotApiMethod<?>> kafkaTemplate;
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@SpyBean(SendMessageMessageSender.class)
	private SendMessageMessageSender sendMessageMessageSender;
	
	@Test
	void getSendMessageUpdates() {
		Long chatId = 111L;
		SendMessage sendMessage = SendMessage.builder().chatId(chatId).text("Text").build();
		willDoNothing().given(sendMessageMessageSender).send(sendMessage);
		
		kafkaTemplate.send(kafkaProperties.getMessageSender().getTopic().getName(), chatId, sendMessage);
		
		await().atMost(Duration.ofSeconds(5)).with().pollInterval(Duration.ofMillis(100))
		       .untilAsserted(() -> then(sendMessageMessageSender).should().send(sendMessage));
	}
}
