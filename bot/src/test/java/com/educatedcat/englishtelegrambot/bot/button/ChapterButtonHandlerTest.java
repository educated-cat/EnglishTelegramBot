package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;

import static org.junit.jupiter.api.Assertions.*;

class ChapterButtonHandlerTest extends AbstractTest {
	@Autowired
	private ChapterButtonHandler chapterButtonHandler;
	
	@Test
	void execute() {
		SendMessage sendMessage = (SendMessage) chapterButtonHandler.execute(new Update() {{
			setMessage(buildMessage());
		}});
		
		assertEquals("Chose the lesson or continue from last one.", sendMessage.getText());
		assertTrue(sendMessage.getReplyMarkup() instanceof LessonKeyboard);
	}
}