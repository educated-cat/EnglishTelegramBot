package com.educatedcat.englishtelegrambot.bot;

import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
import org.telegram.telegrambots.meta.*;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomTest {
	@Configuration
	static class Config {
		@Bean
		@Primary
		public TelegramBotsApi telegramBotsApiMock() {
			return mock(TelegramBotsApi.class);
		}
	}
	
}
