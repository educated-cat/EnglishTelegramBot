package com.educatedcat.englishtelegrambot.bot;

import com.educatedcat.englishtelegrambot.bot.command.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class EnglishTelegramBotTest extends AbstractTest {
	@Autowired
	private StartKeyboard startKeyboard;
	
	@Test
	@SneakyThrows
	void successOnUpdateReceived() {
		Long chatId = super.chatId.incrementAndGet();
		SendMessage expected = new SendMessage(chatId.toString(),
		                                       "Do you want to repeat the words by a course or another way?") {{
			setReplyMarkup(startKeyboard);
		}};
		
		bot.onUpdateReceived(new Update() {{
			setMessage(buildMessage(BotCommandType.START, chatId));
		}});
		
		then(bot).should().execute(expected);
	}
	
	@Test
	@SneakyThrows
	@DisplayName("Caught UnknownCommandException")
	void failedOnUpdateReceived1() {
		Long chatId = super.chatId.incrementAndGet();
		SendMessage expected = new SendMessage(chatId.toString(), "Unknown command.");
		
		bot.onUpdateReceived(new Update() {{
			setMessage(new Message() {{
				setText(String.format("/%s", "unknown_command_9999"));
				setEntities(Collections.singletonList(new MessageEntity("bot_command", 0, 1)));
				setChat(new Chat(chatId, "chat"));
			}});
		}});
		
		then(bot).should().execute(expected);
	}
	
	@Test
	@SneakyThrows
	@DisplayName("Caught UnknownCallbackException")
	void failedOnUpdateReceived2() {
		Long chatId = super.chatId.incrementAndGet();
		SendMessage expected = new SendMessage(chatId.toString(),
		                                       "Unexpected error. Please, send feedback to contact center.");
		
		bot.onUpdateReceived(new Update() {{
			setCallbackQuery(new CallbackQuery() {{
				setMessage(new Message() {{
					setMessageId(1);
					setText("unknown_callback");
					setChat(new Chat(chatId, "chat"));
				}});
			}});
		}});
		
		then(bot).should().execute(expected);
	}
	
	@Test
	@SneakyThrows
	@DisplayName("Caught NotCommandException")
	void failedOnUpdateReceived3() {
		Long chatId = super.chatId.incrementAndGet();
		SendMessage expected = new SendMessage(chatId.toString(), "This is not a command.");
		
		bot.onUpdateReceived(new Update() {{
			setMessage(new Message() {{
				setText("not_test");
				setChat(new Chat(chatId, "chat"));
			}});
		}});
		
		then(bot).should().execute(expected);
	}
	
	@Test
	@SneakyThrows
	@DisplayName("Caught UnsupportedOperationException")
	void failedOnUpdateReceived4() {
		bot.onUpdateReceived(new Update());
		
		then(bot).should(never()).execute(any(SendMessage.class));
	}
	
	@Test
	void getBotUsername() {
		assertEquals("english_bot", bot.getBotUsername());
	}
	
	@Test
	void getBotToken() {
		assertEquals("12345:qwerty", bot.getBotToken());
	}
}