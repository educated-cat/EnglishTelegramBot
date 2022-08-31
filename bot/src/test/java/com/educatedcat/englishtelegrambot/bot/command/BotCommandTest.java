package com.educatedcat.englishtelegrambot.bot.command;

import com.educatedcat.englishtelegrambot.bot.*;
import org.junit.jupiter.api.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;

import static org.junit.jupiter.api.Assertions.*;

class BotCommandTest extends CustomTest {
	
	@Test
	void startBotCommand() {
		SendMessage sendMessage = (SendMessage) handle(new Update() {{
			setMessage(buildMessage(BotCommandType.START));
		}});
		
		assertEquals("Do you want to repeat the words by a course or another way?", sendMessage.getText());
	}
}