package com.educatedcat.englishtelegrambot.botsender.bot;

import com.educatedcat.englishtelegrambot.botsender.kafka.*;
import com.educatedcat.englishtelegrambot.botsender.message.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.kafka.core.*;
import org.springframework.test.context.*;
import org.springframework.web.reactive.function.client.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

import java.time.*;

import static org.awaitility.Awaitility.*;
import static org.mockito.BDDMockito.*;

@MockBeans({
		@MockBean(WebClient.class)
})
@SpringBootTest
class BotReceiverListenerTest {
	private static final KafkaContainer kafkaContainer = new KafkaContainer(
			DockerImageName.parse("confluentinc/cp-kafka:latest"));
	
	@BeforeAll
	public static void beforeAll() {
		kafkaContainer.start();
	}
	
	@AfterAll
	public static void afterAll() {
		kafkaContainer.stop();
	}
	
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
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
	}
}
