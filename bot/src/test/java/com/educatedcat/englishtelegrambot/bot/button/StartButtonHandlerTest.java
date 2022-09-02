package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;

import static org.junit.jupiter.api.Assertions.*;

class StartButtonHandlerTest extends AbstractTest {
	@Autowired
	private StartButtonHandler startButtonHandler;
	
	@Test
	void execute() {
		SendMessage sendMessage = (SendMessage) startButtonHandler.execute(new Update() {{
			setMessage(buildMessage());
		}});
		
		assertEquals("Do you want to repeat the words by a course or another way?", sendMessage.getText());
		assertTrue(sendMessage.getReplyMarkup() instanceof StartKeyboard);
	}
}