package com.educatedcat.englishtelegrambot.bot.callback;

import com.educatedcat.englishtelegrambot.bot.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.objects.*;

import static org.junit.jupiter.api.Assertions.*;

class CallbackHandlerTest extends AbstractTest {
	@Autowired
	private CallbackHandler callbackHandler;
	
	@Test
	void handle() {
		Long chatId = super.chatId.incrementAndGet();
		Update expected = new Update() {{
			setCallbackQuery(new CallbackQuery() {{
				setMessage(new Message() {{
					setMessageId(1);
					setText("unknown_callback");
					//noinspection InconsistentTextBlockIndent
					setData("""
					        {
					        				"button": "START",
					        				"value": ""
					        			}
					        """);
					setChat(new Chat(chatId, "chat"));
				}});
			}});
		}};
		
		EditMessageText editMessage = (EditMessageText) callbackHandler.handle(expected);
		
		assertNotNull(editMessage);
	}
}