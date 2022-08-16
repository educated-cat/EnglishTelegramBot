package com.educatedcat.englishtelegrambot.bot.command;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.keyboard.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.objects.*;

@Component
public class StartBotCommand implements BotCommand {
	private final MessageSource messageSource;
	private final CourseKeyboard courseKeyboard;
	private final StartButtonHandler startButtonHandler;
	
	@Autowired
	public StartBotCommand(MessageSource messageSource, CourseKeyboard courseKeyboard,
	                       StartButtonHandler startButtonHandler) {
		this.messageSource = messageSource;
		this.courseKeyboard = courseKeyboard;
		this.startButtonHandler = startButtonHandler;
	}
	
	@Override
	public BotApiMethod<?> execute(Update update) {
		return startButtonHandler.execute(update);
		/*return new SendMessage(update.getMessage().getChatId().toString(),
		                       messageSource.getMessage("page.start", null, Locale.ENGLISH)) {{
			setReplyMarkup(courseKeyboard);
		}};*/
	}
}
