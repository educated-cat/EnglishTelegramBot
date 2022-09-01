package com.educatedcat.englishtelegrambot.bot.button;

import com.educatedcat.englishtelegrambot.bot.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;

import static org.junit.jupiter.api.Assertions.*;

class CourseButtonHandlerTest extends AbstractTest {
	@Autowired
	private CourseButtonHandler courseButtonHandler;
	
	@Test
	void execute() {
		SendMessage sendMessage = (SendMessage) courseButtonHandler.execute(new Update() {{
			setMessage(buildMessage());
		}});
		
		assertEquals("Chose the chapter or continue from last one.", sendMessage.getText());
		assertTrue(sendMessage.getReplyMarkup() instanceof ChapterKeyboard);
	}
}