package com.educatedcat.englishtelegrambot.botsender.message;

import com.educatedcat.englishtelegrambot.botsender.bot.TelegramBotProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.mock.mockito.SpyBeans;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpyBeans({
		@SpyBean(WebClient.class)
})
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class SendMessageTestMessageSenderTest {
	private static final MockWebServer mockWebServer = new MockWebServer();
	
	@Autowired
	private Map<Class<? extends BotApiMethod<?>>, AbstractMessageSender> abstractMessageSenderMap;
	
	@Autowired
	private TelegramBotProperties botProperties;
	
	@BeforeAll
	@SneakyThrows
	static void beforeAll() {
		mockWebServer.start();
	}
	
	@AfterAll
	@SneakyThrows
	static void afterAll() {
		mockWebServer.shutdown();
	}
	
	@Test
	@SneakyThrows
	void send() {
		SendMessage sendMessage = SendMessage.builder().chatId(111L)
		                                               .text("New Text")
		                                               .build();
		
		abstractMessageSenderMap.get(sendMessage.getClass()).send(sendMessage);
		
		RecordedRequest request = mockWebServer.takeRequest();
		assertEquals(String.format("/%s/%s", botProperties.getToken(), "sendMessage"), request.getPath());
		assertEquals(new ObjectMapper().writeValueAsString(sendMessage), request.getBody().readUtf8());
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("telegram.bot.url.api",
		             () -> String.format("http://localhost:%d", mockWebServer.getPort()));
	}
}
