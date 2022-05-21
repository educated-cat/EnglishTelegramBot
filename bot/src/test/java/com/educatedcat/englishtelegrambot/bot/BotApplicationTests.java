package com.educatedcat.englishtelegrambot.bot;

import com.educatedcat.englishtelegrambot.bot.telegram.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;

import static org.mockito.Mockito.*;

@SpringBootTest
class BotApplicationTests {
	@Configuration
	static class Config {
		@Bean
		@Primary
		public EnglishTelegramBot englishTelegramBotMock() {
			return mock(EnglishTelegramBot.class);
		}
	}
	
	@Test
	void contextLoads() {
	}
	
}
