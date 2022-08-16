package com.educatedcat.englishtelegrambot.bot;

import com.educatedcat.englishtelegrambot.bot.command.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import lombok.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class EnglishTelegramBotTest extends CustomTest {
	@Autowired
	private StartKeyboard startKeyboard;
	
	@Test
	@SneakyThrows
	void successOnUpdateReceived() {
		SendMessage message = new SendMessage("1", "Do you want to repeat the words by a course or another way?") {{
			setReplyMarkup(startKeyboard);
		}};
		
		bot.onUpdateReceived(new Update() {{
			setMessage(buildMessage(BotCommandType.START));
		}});
		
		then(bot).should().execute(message);
	}
	
	@Test
	@SneakyThrows
	void failedOnUpdateReceived() {
		// TODO: improve test
		assertThrows(NullPointerException.class, () -> bot.onUpdateReceived(new Update()));
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