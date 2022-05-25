package com.educatedcat.englishtelegrambot.bot.bot;

import com.educatedcat.englishtelegrambot.bot.*;
import com.educatedcat.englishtelegrambot.bot.bot.command.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.*;
import java.util.concurrent.atomic.*;

import static org.junit.jupiter.api.Assertions.*;

class BotCommandTest extends CustomTest {
	private final AtomicLong chatId = new AtomicLong();
	
	@Autowired
	protected UpdateReceiver updateReceiver;
	
	@Test
	void startBotCommand() {
		SendMessage sendMessage = (SendMessage) updateReceiver.handle(new Update() {{
			setMessage(buildMessage(BotCommandType.START));
		}});
		
		assertEquals("Hello", sendMessage.getText());
	}
	
	private Message buildMessage(BotCommandType commandType) {
		return new Message() {{
			setText(String.format("/%s", commandType.name().toLowerCase()));
			setEntities(Collections.singletonList(new MessageEntity("bot_command", 0, 1)));
			setChat(new Chat(chatId.incrementAndGet(), "chat"));
		}};
	}
}
