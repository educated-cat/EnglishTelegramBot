package com.educatedcat.englishtelegrambot.botsender.message;

import com.educatedcat.englishtelegrambot.botsender.bot.*;
import com.fasterxml.jackson.databind.*;
import lombok.*;
import okhttp3.mockwebserver.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.*;
import org.springframework.web.reactive.function.client.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpyBeans({
		@SpyBean(WebClient.class)
})
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class EditMessageTestMessageSenderTest {
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
		EditMessageText editMessageText = EditMessageText.builder().chatId(111L)
		                                                           .text("New Text")
		                                                           .build();
		
		abstractMessageSenderMap.get(editMessageText.getClass()).send(editMessageText);
		
		RecordedRequest request = mockWebServer.takeRequest();
		assertEquals(String.format("/%s/%s", botProperties.getToken(), "editMessageText"), request.getPath());
		assertEquals(new ObjectMapper().writeValueAsString(editMessageText), request.getBody().readUtf8());
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("telegram.bot.url.api",
		             () -> String.format("http://localhost:%d", mockWebServer.getPort()));
	}
}
