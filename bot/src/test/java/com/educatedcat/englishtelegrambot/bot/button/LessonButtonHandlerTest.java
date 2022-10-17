package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;

import static org.junit.jupiter.api.Assertions.*;

class LessonButtonHandlerTest extends AbstractTest {
	@Autowired
	private LessonCallbackButtonHandler lessonButtonHandler;
	
	@Test
	void execute() {
		SendMessage sendMessage = (SendMessage) lessonButtonHandler.execute(new Update() {{
			setMessage(buildMessage());
		}});
		
		assertEquals("...", sendMessage.getText());
		assertTrue(sendMessage.getReplyMarkup() instanceof WordKeyboard);
	}
}