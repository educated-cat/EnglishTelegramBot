package com.educatedcat.englishtelegrambot.bot;

import com.educatedcat.englishtelegrambot.bot.command.*;
import lombok.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.*;
import java.util.concurrent.atomic.*;

import static org.mockito.BDDMockito.*;

@SpringBootTest
public class CustomTest {
	private final AtomicLong chatId = new AtomicLong();
	
	@Autowired
	protected EnglishTelegramBot bot;
	
	@Autowired
	private UpdateReceiver updateReceiver;
	
	@BeforeEach
	@SneakyThrows
	@SuppressWarnings("unchecked")
	void beforeEach() {
		doReturn(null).when(bot).execute(any(BotApiMethod.class));
//		given(bot.execute(any(BotApiMethod.class))).willReturn(null);
	}
	
	protected BotApiMethod<?> handle(Update update) {
		return updateReceiver.handle(update);
	}
	
	@Configuration
	static class Config {
		@Autowired
		private UpdateReceiver updateReceiver;
		
		@Autowired
		private MessageSource messageSource;
		
		@Value("${telegram.bot.username}")
		private String botUsername;
		@Value("${telegram.bot.token}")
		private String botToken;
		
		@Bean
		@Primary
		public TelegramBotsApi telegramBotsApiMock() {
			return mock(TelegramBotsApi.class);
		}
		
		@Bean
		@Primary
		public EnglishTelegramBot englishTelegramBotSpy() {
			return spy(new EnglishTelegramBot(telegramBotsApiMock(), updateReceiver, messageSource, botUsername,
			                                  botToken));
		}
	}
	
	protected Message buildMessage() {
		return new Message() {{
			setText("text");
			setEntities(Collections.singletonList(new MessageEntity("bot_command", 0, 1)));
			setChat(new Chat(chatId.incrementAndGet(), "chat"));
		}};
	}
	
	protected Message buildMessage(BotCommandType type) {
		return new Message() {{
			setText(String.format("/%s", type.name()));
			setEntities(Collections.singletonList(new MessageEntity("bot_command", 0, 1)));
			setChat(new Chat(chatId.incrementAndGet(), "chat"));
		}};
	}
}
