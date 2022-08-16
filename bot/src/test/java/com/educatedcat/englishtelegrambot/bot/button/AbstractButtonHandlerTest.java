package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.objects.*;

import static org.junit.jupiter.api.Assertions.*;

class AbstractButtonHandlerTest extends CustomTest {
	@Autowired
	private ChapterButtonHandler chapterButtonHandler;
	
	@Test
	void executeEditableMessage() {
		BotApiMethod<?> editMessage = chapterButtonHandler.execute(new Update() {{
			Message message = buildMessage();
			message.setEntities(null);
			message.setMessageId(1);
			setMessage(message);
		}});
		
		assertTrue(editMessage instanceof EditMessageText);
	}
	
	@Test
	void executeWithCallbackQuery() {
		BotApiMethod<?> editMessage = chapterButtonHandler.execute(new Update() {{
			Message message = buildMessage();
			message.setMessageId(1);
			setCallbackQuery(new CallbackQuery() {{
				setMessage(message);
			}});
		}});
		
		assertTrue(editMessage instanceof EditMessageText);
	}
	
	@Test
	void executeWithoutMessageAndCallbackQuery() {
		assertThrows(IllegalArgumentException.class, () -> chapterButtonHandler.execute(new Update()));
	}
	
}